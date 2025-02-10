package dev.himitery.coupon.modules.coupon.adapter.out.persistence

import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import org.springframework.data.jpa.repository.JpaRepository

interface CouponSetRepository : JpaRepository<CouponSet, String>