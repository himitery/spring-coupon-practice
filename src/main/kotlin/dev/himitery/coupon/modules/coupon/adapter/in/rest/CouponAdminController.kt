package dev.himitery.coupon.modules.coupon.adapter.`in`.rest

import dev.himitery.coupon.modules.coupon.adapter.`in`.rest.dto.AdminCreateCouponReq
import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponSetRes
import dev.himitery.coupon.modules.coupon.application.port.`in`.CouponAdminUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin/coupon")
class CouponAdminController(private val couponAdminUseCase: CouponAdminUseCase) {

    @PostMapping("/new")
    fun create(@RequestBody req: AdminCreateCouponReq): ResponseEntity<CouponSetRes> {
        return ResponseEntity.ok(couponAdminUseCase.createCouponSet(req.toDomain()))
    }
}