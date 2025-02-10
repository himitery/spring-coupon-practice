package dev.himitery.coupon.modules.coupon.application.dto.`in`

import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import java.time.LocalDateTime

data class CouponSetRes(
    val id: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
    val name: String,
    val amount: Long,
    val remain: Long,
) {
    companion object {
        fun fromDomain(couponSet: CouponSet): CouponSetRes {
            return CouponSetRes(
                id = couponSet.id,
                createdAt = couponSet.createdAt,
                modifiedAt = couponSet.modifiedAt,
                name = couponSet.name,
                amount = couponSet.amount,
                remain = couponSet.remain,
            )
        }
    }
}