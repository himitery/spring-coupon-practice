package dev.himitery.coupon.modules.coupon.domain.exception

class CouponLockException : IllegalArgumentException() {
    override val message: String = "[ERROR] 쿠폰 발급에 실패했습니다."
}