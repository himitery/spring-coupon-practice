package dev.himitery.coupon.modules.coupon.application.service

import dev.himitery.coupon.modules.coupon.domain.model.CouponSet

interface CouponSetService {
    fun save(couponSet: CouponSet): CouponSet
    fun findById(id: String): CouponSet?
}