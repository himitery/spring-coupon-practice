package dev.himitery.coupon.modules.coupon.domain.service

import dev.himitery.coupon.modules.coupon.application.port.out.CouponSetPersistencePort
import dev.himitery.coupon.modules.coupon.application.service.CouponSetService
import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CouponSetServiceImpl(
    private val persistencePort: CouponSetPersistencePort,
) : CouponSetService {

    @Transactional
    override fun save(couponSet: CouponSet): CouponSet {
        return persistencePort.save(couponSet)
    }

    override fun findById(id: String): CouponSet? {
        return persistencePort.findById(id)
    }
}