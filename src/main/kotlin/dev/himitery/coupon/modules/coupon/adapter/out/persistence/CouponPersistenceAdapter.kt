package dev.himitery.coupon.modules.coupon.adapter.out.persistence

import dev.himitery.coupon.modules.coupon.application.port.out.CouponPersistencePort
import dev.himitery.coupon.modules.coupon.domain.model.Coupon
import org.springframework.stereotype.Component

@Component
class CouponPersistenceAdapter(private val repository: CouponRepository) : CouponPersistencePort {

    override fun findByUserIdAndCouponSetId(userId: String, couponSetId: String): Coupon? {
        return repository.findByUserIdAndCouponSetId(userId, couponSetId)
    }

    override fun save(coupon: Coupon): Coupon {
        return repository.save(coupon)
    }
}