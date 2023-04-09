package com.looko.firewallmanager.webapi.tencent

import com.looko.firewallmanager.entity.InstanceResponse
import com.looko.firewallmanager.webapi.InstanceOperation
import org.springframework.stereotype.Component

@Component
class LighthouseInstance: InstanceOperation {
    override fun listAllInstances(): InstanceResponse {
        TODO("Not yet implemented")
    }
}