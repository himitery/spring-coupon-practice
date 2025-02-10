package dev.himitery.coupon.modules.coupon.application.facade

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponSetRes
import dev.himitery.coupon.modules.coupon.application.dto.`in`.CreateCouponSetReq
import dev.himitery.coupon.modules.coupon.application.port.`in`.CouponAdminUseCase
import dev.himitery.coupon.modules.coupon.application.service.CouponSetService
import dev.himitery.coupon.modules.coupon.domain.model.CouponSet
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CouponAdminFacade(private val couponSetService: CouponSetService) : CouponAdminUseCase {

    @Transactional
    override fun createCouponSet(req: CreateCouponSetReq): CouponSetRes {
        val couponSet = CouponSet(
            name = req.name,
            amount = req.amount,
            remain = req.amount,
        )

        return CouponSetRes.fromDomain(couponSetService.save(couponSet))
    }
}
