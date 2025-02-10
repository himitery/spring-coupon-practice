package dev.himitery.coupon.modules.coupon.adapter.`in`.rest

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponRes
import dev.himitery.coupon.modules.coupon.application.port.`in`.CouponUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/coupon")
class CouponController(private val couponUseCase: CouponUseCase) {

    @PostMapping("/{couponSet}/issue")
    fun issue(
        @RequestHeader("X-USER-ID") userId: String,
        @PathVariable("couponSet") couponSet: String,
    ): ResponseEntity<CouponRes> {
        return ResponseEntity.ok(couponUseCase.issue(userId, couponSet))
    }
}