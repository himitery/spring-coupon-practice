package dev.himitery.coupon.shared.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty


@ConfigurationProperties(prefix = "coupon")
data class CouponProperty(@NestedConfigurationProperty val code: Code) {
    data class Code(val length: Int)
}