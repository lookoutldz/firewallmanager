package com.looko.firewallmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class FirewallmanagerApplication

fun main(args: Array<String>) {
    runApplication<FirewallmanagerApplication>(*args)
}
