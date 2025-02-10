package dev.himitery.coupon.modules.coupon.domain.exception

class NotFoundCouponSetException(id: String) : IllegalArgumentException() {
    override val message: String = "[ERROR] 쿠폰을 찾을 수 없습니다: ($id)"
}