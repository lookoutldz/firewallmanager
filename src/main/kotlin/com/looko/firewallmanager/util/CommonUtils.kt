package com.looko.firewallmanager.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val Any.log: Logger
    get() = LoggerFactory.getLogger(this::class.java)

val redisKey = "LOCAL_IP_MAP"

