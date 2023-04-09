package com.looko.firewallmanager

import com.looko.firewallmanager.service.FirewallUpdateService
import com.looko.firewallmanager.util.redisKey
import com.looko.firewallmanager.webapi.local.LocalAddressFetcher
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class FirewallmanagerApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired
    private lateinit var localAddressFetcher: LocalAddressFetcher

    @Test
    fun getLocalIp() {
        println(localAddressFetcher.fetch())
    }

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    lateinit var firewallUpdateService: FirewallUpdateService
    @Test
    fun redisTest() {
        val hOps = redisTemplate.opsForHash<String, Long>()
        hOps.put(redisKey, "k1", System.currentTimeMillis())
        firewallUpdateService.purgeExpireHash()
    }


}
