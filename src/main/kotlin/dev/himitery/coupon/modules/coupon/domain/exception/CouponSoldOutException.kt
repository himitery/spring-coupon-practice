package dev.himitery.coupon.modules.coupon.domain.exception

class CouponSoldOutException(couponSetId: String) : IllegalArgumentException() {
    override val message: String = "[ERROR] 쿠폰이 모두 소진되었습니다: ($couponSetId)"
}