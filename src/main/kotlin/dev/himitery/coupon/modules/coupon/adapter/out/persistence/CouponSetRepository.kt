package dev.himitery.coupon.modules.coupon.adapter.out.persistence

import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.util.*

interface CouponSetRepository : JpaRepository<CouponSet, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: String): Optional<CouponSet>
}
