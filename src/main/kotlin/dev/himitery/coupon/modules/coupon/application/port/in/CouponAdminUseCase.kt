package dev.himitery.coupon.modules.coupon.application.port.`in`

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponSetRes
import dev.himitery.coupon.modules.coupon.application.dto.`in`.CreateCouponSetReq

interface CouponAdminUseCase {
    fun createCouponSet(req: CreateCouponSetReq): CouponSetRes
}