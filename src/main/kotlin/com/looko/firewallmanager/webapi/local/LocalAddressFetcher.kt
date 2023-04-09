package com.looko.firewallmanager.webapi.local

import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern

@Component
class LocalAddressFetcher {

    private final val cipcc = "https://cip.cc"

    private final val pattern = Pattern.compile("<pre>IP\t: (.*)")

    fun fetch(): String? {
        return with(URL(cipcc).openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            connect()
            if (responseCode == 200) match(String(inputStream.readAllBytes())) else null
        }
    }

    private fun match(content: String): String? {
        return with(pattern.matcher(content)) {
            if (find()) group(1).trim() else null
        }
    }
}