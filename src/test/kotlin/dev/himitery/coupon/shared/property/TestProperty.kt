package dev.himitery.coupon.shared.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty


@ConfigurationProperties(prefix = "test")
data class TestProperty(@NestedConfigurationProperty val couponSetId: CouponSetId) {
    data class CouponSetId(val full: String, val one: String, val zero: String)
}