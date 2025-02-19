package dev.himitery.coupon.modules.coupon.domain.model

import dev.himitery.coupon.shared.domain.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Version
import java.util.*

@Entity
class CouponSet(
    @Column(nullable = false) val name: String,
    @Column(nullable = false) var amount: Long,
    @Column(nullable = false) var remain: Long
) : BaseEntity() {

    @Id
    @Column(nullable = false, unique = true)
    val id: String = UUID.randomUUID().toString()

    @Version
    private var version: Long = 0
}
