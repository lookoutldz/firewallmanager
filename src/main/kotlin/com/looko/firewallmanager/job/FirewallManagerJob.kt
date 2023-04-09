package com.looko.firewallmanager.job

import com.looko.firewallmanager.service.FirewallUpdateService
import com.looko.firewallmanager.util.log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class FirewallManagerJob {

    @Autowired
    private lateinit var firewallUpdateService: FirewallUpdateService

    /**
     * 一分钟一次
     */
    @Scheduled(cron = "0 0/1 * * * *")
    fun updateRedisJob() {
        log.info("================== 更新 Redis IP 池任务 =====================")
        firewallUpdateService.updateAddressPool()
        log.info("================== 更新 Redis IP 池任务结束 =====================")
    }

    /**
     * 两分钟一次
     */
    @Scheduled(cron = "15 0/2 * * * *")
    fun updateFirewall() {
        log.info("================== 更新防火墙任务 =====================")
        firewallUpdateService.updateRemoteFirewall()
        log.info("================== 更新防火墙任务结束 =====================")
    }
}