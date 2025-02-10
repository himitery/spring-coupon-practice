package dev.himitery.coupon.modules.coupon.application.port.out

import dev.himitery.coupon.modules.coupon.domain.model.Coupon

interface CouponPersistencePort {
    fun findByUserIdAndCouponSetId(userId: String, couponSetId: String): Coupon?
    fun save(coupon: Coupon): Coupon
}