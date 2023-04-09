package com.looko.firewallmanager.service

import com.looko.firewallmanager.entity.tencent.FirewallRuleSetResponse
import com.looko.firewallmanager.util.log
import com.looko.firewallmanager.util.redisKey
import com.looko.firewallmanager.webapi.ServerParam
import com.looko.firewallmanager.webapi.local.LocalAddressFetcher
import com.looko.firewallmanager.webapi.tencent.LighthouseFirewall
import com.tencentcloudapi.lighthouse.v20200324.models.FirewallRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class FirewallUpdateService {

    private final val expireMillis = 1000 * 60 * 60 * 24 * 3

    @Autowired
    private lateinit var localAddressFetcher: LocalAddressFetcher

    @Autowired
    private lateinit var serverParam: ServerParam

    @Autowired
    private lateinit var lighthouseFirewall: LighthouseFirewall

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    private val hOps by lazy { redisTemplate.opsForHash<String, Long>() }

    fun updateAddressPool() {
        localAddressFetcher.fetch()?.let { ip ->
            purgeExpireHash()
            log.info("更新 [$ip/32] 到 Redis")
            val currentMillis = System.currentTimeMillis()
            hOps.put(redisKey, "$ip/32", currentMillis + expireMillis)
        }
    }

    fun purgeExpireHash() {
        if (!redisTemplate.hasKey(redisKey)) return

        val currentMillis = System.currentTimeMillis()
        hOps.entries(redisKey)
            .filterValues { currentMillis >= it }
            .keys
            .mapNotNull { it }
            .apply {
                if (this.isEmpty())
                    return@apply
                hOps.delete(redisKey, *this.toTypedArray())
                log.info("已清除 Redis 过期IP：${this.joinToString { it }}")
            }
    }

    fun updateRemoteFirewall() {
        val ips = hOps.entries(redisKey)
            .keys
            .toSet()
        log.info("Redis IP 池现状：${ips.joinToString { it }}")

        if (ips.isEmpty()) return

        val firewallIps = (lighthouseFirewall.listFirewallRules() as FirewallRuleSetResponse)
            .firewallRuleSet
            .filter {
                it.action == serverParam.action
                        && it.port == serverParam.port
                        && it.protocol == serverParam.protocol
                        && it.firewallRuleDescription == serverParam.firewallRuleDescription
            }
            .map { it.cidrBlock }
            .toSet()

        log.info("服务器防火墙 IP现状：${firewallIps.joinToString { it }}")

        ips.subtract(firewallIps)
            .mapNotNull { it }
            .map {
                FirewallRule().apply {
                    protocol = serverParam.protocol
                    port = serverParam.port
                    cidrBlock = it
                    action = serverParam.action
                    firewallRuleDescription = serverParam.firewallRuleDescription
                }
            }
            .toTypedArray()
            .apply {
                if (this.isEmpty())
                    return@apply
                lighthouseFirewall.addFirewallRules(this)
                log.info("已向服务器添加IP: ${this.joinToString { it.cidrBlock }}")
            }

        firewallIps.subtract(ips)
            .mapNotNull { it }
            .map {
                FirewallRule().apply {
                    protocol = serverParam.protocol
                    port = serverParam.port
                    cidrBlock = it
                    action = serverParam.action
                    firewallRuleDescription = serverParam.firewallRuleDescription
                }
            }
            .toTypedArray()
            .apply {
                if (this.isEmpty())
                    return@apply
                lighthouseFirewall.deleteFirewallRules(this)
                log.info("已从服务器移除IP：${this.joinToString { it.cidrBlock }}")
            }
    }
}