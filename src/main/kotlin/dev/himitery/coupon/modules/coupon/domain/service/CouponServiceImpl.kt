package dev.himitery.coupon.modules.coupon.domain.service

import dev.himitery.coupon.modules.coupon.application.port.out.CouponPersistencePort
import dev.himitery.coupon.modules.coupon.application.service.CouponService
import dev.himitery.coupon.modules.coupon.domain.model.Coupon
import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import dev.himitery.coupon.shared.property.CouponProperty
import dev.himitery.coupon.shared.utils.RandomUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CouponServiceImpl(
    private val couponProperty: CouponProperty,
    private val randomUtils: RandomUtils,
    private val persistencePort: CouponPersistencePort,
) : CouponService {

    override fun find(userId: String, couponSet: CouponSet): Coupon? {
        return persistencePort.findByUserIdAndCouponSetId(userId, couponSet.id)
    }

    @Transactional
    override fun issue(userId: String, couponSet: CouponSet): Coupon {
        val coupon = Coupon(
            randomUtils.generate(couponProperty.code.length),
            couponSet,
            userId
        )

        return persistencePort.save(coupon)
    }
}