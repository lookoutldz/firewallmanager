package com.looko.firewallmanager.webapi

import com.looko.firewallmanager.entity.FirewallResponse
import com.tencentcloudapi.lighthouse.v20200324.models.FirewallRule

interface FirewallOperation {
    fun listFirewallRules(): FirewallResponse
    fun addFirewallRules(firewallRules: Array<FirewallRule>)
    fun deleteFirewallRules(firewallRules: Array<FirewallRule>)
}