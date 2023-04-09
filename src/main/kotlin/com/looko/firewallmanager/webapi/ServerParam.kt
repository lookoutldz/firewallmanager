package com.looko.firewallmanager.webapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ServerParam {

    @Value("\${secretId}")
    lateinit var secretId: String

    @Value("\${secretKey}")
    lateinit var secretKey: String

    @Value("\${region}")
    lateinit var region: String

    @Value("\${instanceId}")
    lateinit var instanceId: String

    @Value("\${rule.protocol}")
    lateinit var protocol: String

    @Value("\${rule.port}")
    lateinit var port: String

    @Value("\${rule.action}")
    lateinit var action: String

    @Value("\${rule.firewallRuleDescription}")
    lateinit var firewallRuleDescription: String

}