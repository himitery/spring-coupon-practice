package dev.himitery.coupon.modules.coupon.adapter.`in`.rest

import dev.himitery.coupon.modules.coupon.domain.exception.CouponLockException
import dev.himitery.coupon.modules.coupon.domain.exception.CouponSoldOutException
import dev.himitery.coupon.modules.coupon.domain.exception.NotFoundCouponSetException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CouponControllerAdvice {

    @ExceptionHandler(NotFoundCouponSetException::class)
    fun handleNotFoundCouponSetException(e: NotFoundCouponSetException): ResponseEntity<String> {
        return ResponseEntity.status(404).body(e.message)
    }

    @ExceptionHandler(CouponSoldOutException::class)
    fun handleCouponSoldOutException(e: CouponSoldOutException): ResponseEntity<String> {
        return ResponseEntity.status(404).body(e.message)
    }

    @ExceptionHandler(CouponLockException::class)
    fun handleCouponLockException(e: CouponLockException): ResponseEntity<String> {
        return ResponseEntity.status(500).body(e.message)
    }
}
