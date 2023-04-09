package com.looko.firewallmanager.webapi.tencent

import com.google.gson.Gson
import com.looko.firewallmanager.entity.FirewallResponse
import com.looko.firewallmanager.entity.tencent.FirewallRuleSetResponse
import com.looko.firewallmanager.util.log
import com.looko.firewallmanager.webapi.FirewallOperation
import com.looko.firewallmanager.webapi.ServerParam
import com.tencentcloudapi.common.Credential
import com.tencentcloudapi.lighthouse.v20200324.LighthouseClient
import com.tencentcloudapi.lighthouse.v20200324.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LighthouseFirewall: FirewallOperation {

    @Autowired
    private lateinit var serverParam: ServerParam

    override fun listFirewallRules(): FirewallResponse {
        return with(serverParam) {
            val client = LighthouseClient(Credential(secretId, secretKey), region)
            val req = DescribeFirewallRulesRequest()
                .apply { instanceId = this@with.instanceId }
            val resp = client.DescribeFirewallRules(req)
            val json = DescribeFirewallRulesResponse.toJsonString(resp)
            log.debug(json)
            Gson().fromJson(json, FirewallRuleSetResponse::class.java)
        }
    }

    override fun addFirewallRules(firewallRules: Array<FirewallRule>) {
        with(serverParam) {
            val client = LighthouseClient(Credential(secretId, secretKey), region)
            val req = CreateFirewallRulesRequest()
                .apply {
                    instanceId = this@with.instanceId
                    this.firewallRules = firewallRules
                }
            val resp = client.CreateFirewallRules(req)
            log.debug(CreateFirewallRulesResponse.toJsonString(resp))
        }
    }

    override fun deleteFirewallRules(firewallRules: Array<FirewallRule>) {
        with(serverParam) {
            val client = LighthouseClient(Credential(secretId, secretKey), region)
            val req = DeleteFirewallRulesRequest()
                .apply {
                    instanceId = this@with.instanceId
                    this.firewallRules = firewallRules
                }
            val resp = client.DeleteFirewallRules(req)
            log.debug(DeleteFirewallRulesResponse.toJsonString(resp))
        }
    }

}