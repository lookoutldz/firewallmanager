package com.looko.firewallmanager.webapi

import com.looko.firewallmanager.entity.InstanceResponse

interface InstanceOperation {
    fun listAllInstances(): InstanceResponse
}