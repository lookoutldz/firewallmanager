package com.looko.firewallmanager.webapi

import com.looko.firewallmanager.webapi.tencent.LighthouseFirewall
import com.tencentcloudapi.lighthouse.v20200324.models.FirewallRule
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("prod")
@SpringBootTest
class TencentApiTest {

    @Autowired
    lateinit var lighthouseFirewall: LighthouseFirewall

    private final val rules = arrayOf(
        FirewallRule().apply {
            protocol = "TCP"
            port = "12346"
            cidrBlock = "0.0.0.0/32"
            action = "ACCEPT"
            firewallRuleDescription = "for test"
        }
    )

    @Disabled
    @Test
    fun testAddFirewallRules() {
        lighthouseFirewall.addFirewallRules(rules)
    }

    @Test
    fun testListFirewallRules() {
        val rules = lighthouseFirewall.listFirewallRules()
        println(rules)
    }

    @Disabled
    @Test
    fun testDeleteFirewallRules() {
        lighthouseFirewall.deleteFirewallRules(rules)
    }
}