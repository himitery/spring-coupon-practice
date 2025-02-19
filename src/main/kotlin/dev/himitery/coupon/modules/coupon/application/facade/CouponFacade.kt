package dev.himitery.coupon.modules.coupon.application.facade

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponRes
import dev.himitery.coupon.modules.coupon.application.port.`in`.CouponUseCase
import dev.himitery.coupon.modules.coupon.application.service.CouponService
import dev.himitery.coupon.modules.coupon.application.service.CouponSetService
import dev.himitery.coupon.modules.coupon.domain.exception.CouponLockException
import dev.himitery.coupon.modules.coupon.domain.exception.CouponSoldOutException
import dev.himitery.coupon.modules.coupon.domain.exception.NotFoundCouponSetException
import jakarta.persistence.OptimisticLockException
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CouponFacade(
    private val couponService: CouponService,
    private val couponSetService: CouponSetService,
) : CouponUseCase {

    companion object {
        private const val RETRY_COUNT = 5
    }

    @Transactional
    @Retryable(
        maxAttempts = RETRY_COUNT,
        noRetryFor = [CouponSoldOutException::class],
        backoff = Backoff(delay = 100),
    )
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

    @Recover
    protected fun handleRetryFailure(e: Exception, userId: String, couponSetId: String): CouponRes {
        when (e) {
            is OptimisticLockingFailureException, is OptimisticLockException -> throw CouponLockException()
            else -> throw e
        }
    }
}