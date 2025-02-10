package dev.himitery.coupon.modules.coupon.application.port.out

import dev.himitery.coupon.modules.coupon.domain.model.CouponSet

interface CouponSetPersistencePort {
    fun save(couponSet: CouponSet): CouponSet
    fun findById(id: String): CouponSet?
}