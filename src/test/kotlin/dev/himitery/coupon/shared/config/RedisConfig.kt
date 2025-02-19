package dev.himitery.coupon.shared.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.GenericContainer


@Configuration
class RedisConfig(private val redisContainer: GenericContainer<*>) {

    @Bean
    fun redissonClient(): RedissonClient {
        return Redisson.create(
            Config().apply {
                useSingleServer().apply {
                    address = "redis://${redisContainer.host}:${redisContainer.firstMappedPort}"
                }
            }
        )
    }
}