package dev.himitery.coupon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringCouponPracticeApplication

fun main(args: Array<String>) {
    runApplication<SpringCouponPracticeApplication>(*args)
}
