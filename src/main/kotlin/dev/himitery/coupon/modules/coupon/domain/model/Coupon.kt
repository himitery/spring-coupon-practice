package dev.himitery.coupon.modules.coupon.domain.model

import dev.himitery.coupon.shared.domain.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Coupon(
    @Id @Column(nullable = false, unique = true) val code: String,
    @ManyToOne(optional = false) var couponSet: CouponSet,
    @Column(nullable = false) val userId: String
) : BaseEntity()
