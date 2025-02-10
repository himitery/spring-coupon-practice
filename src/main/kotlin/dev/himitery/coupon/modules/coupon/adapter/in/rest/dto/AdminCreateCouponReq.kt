package dev.himitery.coupon.modules.coupon.adapter.`in`.rest.dto

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CreateCouponSetReq

data class AdminCreateCouponReq(
    val name: String,
    val amount: Long,
) {
    fun toDomain() = CreateCouponSetReq(
        name = name,
        amount = amount,
    )
}
