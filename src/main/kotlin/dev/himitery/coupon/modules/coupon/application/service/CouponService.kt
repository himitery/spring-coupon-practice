package dev.himitery.coupon.modules.coupon.application.service

import dev.himitery.coupon.modules.coupon.domain.model.Coupon
import dev.himitery.coupon.modules.coupon.domain.model.CouponSet

interface CouponService {
    fun find(userId: String, couponSet: CouponSet): Coupon?
    fun issue(userId: String, couponSet: CouponSet): Coupon
}