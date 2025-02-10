package dev.himitery.coupon.modules.coupon.application.facade

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponRes
import dev.himitery.coupon.modules.coupon.application.port.`in`.CouponUseCase
import dev.himitery.coupon.modules.coupon.application.service.CouponService
import dev.himitery.coupon.modules.coupon.application.service.CouponSetService
import dev.himitery.coupon.modules.coupon.domain.exception.CouponSoldOutException
import dev.himitery.coupon.modules.coupon.domain.exception.NotFoundCouponSetException
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CouponFacade(
    private val couponService: CouponService,
    private val couponSetService: CouponSetService,
) : CouponUseCase {

    @Transactional
    @Cacheable(value = ["coupon"], key = "#userId + '::' + #couponSetId")
    override fun issue(userId: String, couponSetId: String): CouponRes {
        val couponSet = couponSetService.findById(couponSetId) ?: throw NotFoundCouponSetException(couponSetId)
        if (couponSet.remain == 0L) {
            throw CouponSoldOutException(couponSetId)
        }

        val coupon = couponService.issue(userId, couponSet).also {
            couponSet.remain -= 1
        }

        return CouponRes.fromDomain(coupon)
    }
}