package dev.himitery.coupon.shared.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.context.annotation.Profile


@Profile("test")
@ConfigurationProperties(prefix = "coupon")
data class CouponProperty(
    @NestedConfigurationProperty val testId: TestID,
    @NestedConfigurationProperty val code: Code,
) {
    data class TestID(val full: String, val one: String, val zero: String)
    data class Code(val length: Int)
}