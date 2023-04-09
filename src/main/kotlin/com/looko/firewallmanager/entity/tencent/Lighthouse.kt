package com.looko.firewallmanager.entity.tencent
import com.google.gson.annotations.SerializedName
import com.looko.firewallmanager.entity.FirewallResponse
import com.looko.firewallmanager.entity.InstanceResponse

// lighthouse

data class LighthouseInstanceResponse(
    @SerializedName("Response")
    val lighthouseInstanceSubResponse: LighthouseInstanceSubResponse
): InstanceResponse

data class LighthouseInstanceSubResponse(
    @SerializedName("InstanceSet")
    val instanceSet: List<InstanceSet>,
    @SerializedName("RequestId")
    val requestId: String,
    @SerializedName("TotalCount")
    val totalCount: Int
)

data class InstanceSet(
    @SerializedName("BlueprintId")
    val blueprintId: String,
    @SerializedName("BundleId")
    val bundleId: String,
    @SerializedName("CPU")
    val cPU: Int,
    @SerializedName("CreatedTime")
    val createdTime: String,
    @SerializedName("ExpiredTime")
    val expiredTime: String,
    @SerializedName("InstanceChargeType")
    val instanceChargeType: String,
    @SerializedName("InstanceId")
    val instanceId: String,
    @SerializedName("InstanceName")
    val instanceName: String,
    @SerializedName("InstanceRestrictState")
    val instanceRestrictState: String,
    @SerializedName("InstanceState")
    val instanceState: String,
    @SerializedName("InternetAccessible")
    val internetAccessible: InternetAccessible,
    @SerializedName("IsolatedTime")
    val isolatedTime: String?,
    @SerializedName("LatestOperation")
    val latestOperation: String,
    @SerializedName("LatestOperationRequestId")
    val latestOperationRequestId: String,
    @SerializedName("LatestOperationState")
    val latestOperationState: String,
    @SerializedName("LoginSettings")
    val loginSettings: LoginSettings,
    @SerializedName("Memory")
    val memory: Int,
    @SerializedName("OsName")
    val osName: String,
    @SerializedName("Platform")
    val platform: String,
    @SerializedName("PlatformType")
    val platformType: String,
    @SerializedName("PrivateAddresses")
    val privateAddresses: List<String>,
    @SerializedName("PublicAddresses")
    val publicAddresses: List<String>,
    @SerializedName("RenewFlag")
    val renewFlag: String,
    @SerializedName("SystemDisk")
    val systemDisk: SystemDisk,
    @SerializedName("Tags")
    val tags: List<String>,
    @SerializedName("Uuid")
    val uuid: String,
    @SerializedName("Zone")
    val zone: String
)

data class InternetAccessible(
    @SerializedName("InternetChargeType")
    val internetChargeType: String,
    @SerializedName("InternetMaxBandwidthOut")
    val internetMaxBandwidthOut: Int,
    @SerializedName("PublicIpAssigned")
    val publicIpAssigned: Boolean
)

data class LoginSettings(
    @SerializedName("KeyIds")
    val keyIds: List<String>
)

data class SystemDisk(
    @SerializedName("DiskId")
    val diskId: String,
    @SerializedName("DiskSize")
    val diskSize: Int,
    @SerializedName("DiskType")
    val diskType: String
)

// firewall

data class FirewallRuleSetResponse(
    @SerializedName("FirewallRuleSet")
    val firewallRuleSet: List<FirewallRuleSet>,
    @SerializedName("FirewallVersion")
    val firewallVersion: Int,
    @SerializedName("RequestId")
    val requestId: String,
    @SerializedName("TotalCount")
    val totalCount: Int
): FirewallResponse

data class FirewallRuleSet(
    @SerializedName("Action")
    val action: String,
    @SerializedName("AppType")
    val appType: String,
    @SerializedName("CidrBlock")
    val cidrBlock: String,
    @SerializedName("FirewallRuleDescription")
    val firewallRuleDescription: String,
    @SerializedName("Port")
    val port: String,
    @SerializedName("Protocol")
    val protocol: String
)