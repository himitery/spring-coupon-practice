package dev.himitery.coupon

import dev.himitery.coupon.shared.config.TestcontainersConfig
import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<SpringCouponPracticeApplication>()
        .with(TestcontainersConfig::class)
        .run(*args)
}
