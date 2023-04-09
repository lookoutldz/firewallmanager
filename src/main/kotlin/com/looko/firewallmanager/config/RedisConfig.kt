package com.looko.firewallmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            setConnectionFactory(factory)
            val stringRedisSerializer = StringRedisSerializer()
            val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer()
            keySerializer = stringRedisSerializer
            valueSerializer = genericJackson2JsonRedisSerializer
            hashKeySerializer = stringRedisSerializer
            hashValueSerializer = genericJackson2JsonRedisSerializer
            afterPropertiesSet()
        }
    }
}