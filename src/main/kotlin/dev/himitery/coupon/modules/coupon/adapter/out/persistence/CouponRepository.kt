package dev.himitery.coupon.modules.coupon.adapter.out.persistence

import dev.himitery.coupon.modules.coupon.domain.model.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository : JpaRepository<Coupon, String> {
    fun findByUserIdAndCouponSetId(userId: String, couponSetId: String): Coupon?
}