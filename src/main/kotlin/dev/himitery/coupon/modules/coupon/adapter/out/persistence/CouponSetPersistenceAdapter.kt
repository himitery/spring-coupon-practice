package dev.himitery.coupon.modules.coupon.adapter.out.persistence

import dev.himitery.coupon.modules.coupon.application.port.out.CouponSetPersistencePort
import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import org.springframework.stereotype.Component

@Component
class CouponSetPersistenceAdapter(
    private val repository: CouponSetRepository,
) : CouponSetPersistencePort {

    override fun save(couponSet: CouponSet): CouponSet {
        return repository.save(couponSet)
    }

    override fun findById(id: String): CouponSet? {
        return repository.findById(id).orElse(null)
    }
}