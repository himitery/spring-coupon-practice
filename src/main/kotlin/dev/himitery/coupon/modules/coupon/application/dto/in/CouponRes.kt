package dev.himitery.coupon.modules.coupon.application.dto.`in`

import dev.himitery.coupon.modules.coupon.domain.model.Coupon
import java.io.Serializable
import java.time.LocalDateTime

data class CouponRes(
    val code: String,
    val user: String,
    val createdAt: LocalDateTime,
) : Serializable {
    companion object {
        fun fromDomain(coupon: Coupon): CouponRes {
            return CouponRes(
                code = coupon.code,
                user = coupon.userId,
                createdAt = coupon.createdAt,
            )
        }
    }
}