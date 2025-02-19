package dev.himitery.coupon.shared.config

import dev.himitery.coupon.shared.property.RedisProperty
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedisConfig(private val redisProperty: RedisProperty) {

    @Bean
    fun redissonClient(): RedissonClient {
        return Redisson.create(
            Config().apply {
                useSingleServer().apply {
                    address = "redis://${redisProperty.host}:${redisProperty.port}"
                }
            }
        )
    }
}