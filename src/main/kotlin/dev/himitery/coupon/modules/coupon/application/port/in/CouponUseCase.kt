package dev.himitery.coupon.modules.coupon.application.port.`in`

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponRes

interface CouponUseCase {
    fun issue(userId: String, couponSetId: String): CouponRes
}