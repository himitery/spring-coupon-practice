package dev.himitery.coupon.modules.coupon.application.facade

import dev.himitery.coupon.modules.coupon.application.dto.`in`.CouponRes
import dev.himitery.coupon.modules.coupon.domain.exception.CouponSoldOutException
import dev.himitery.coupon.shared.config.TestcontainersConfig
import dev.himitery.coupon.shared.property.TestProperty
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.common.runBlocking
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@Import(TestcontainersConfig::class)
@Sql("/data.sql")
class CouponFacadeTest(
    private val couponFacade: CouponFacade,
    private val testProperty: TestProperty,
) : FunSpec({

    test("쿠폰이 남아 있다면 발급 받는다.") {
        // given
        val userId = "test_user"
        val couponSetId = testProperty.couponSetId.full

        // when
        val coupon = couponFacade.issue(userId, couponSetId)

        // then
        coupon.shouldNotBeNull()
        coupon.shouldBeInstanceOf<CouponRes>()
    }

    test("쿠폰이 남아 있지 않다면 발급에 실패한다.") {
        // given
        val userId = "test_user"
        val couponSetId = testProperty.couponSetId.zero

        // when
        val throwable = { couponFacade.issue(userId, couponSetId) }

        // then
        shouldThrow<CouponSoldOutException> { throwable() }
    }

    test("동시에 쿠폰 발급을 시도해도 원자성이 보장된다.") {
        // given
        val userCount = 1_000
        val couponSetId = testProperty.couponSetId.one

        // when
        val res = runBlocking {
            (1..userCount).map { idx ->
                async(Dispatchers.IO) {
                    runCatching {
                        couponFacade.issue("test_user_$idx", couponSetId)
                    }
                }
            }.awaitAll()
        }

        // then
        res.filter { it.isSuccess }.size.shouldBeEqual(1)
        res.filter { it.isFailure }.size.shouldBeEqual(userCount - 1)
        res.filter { it.isFailure }.forEach {
            it.exceptionOrNull().shouldBeInstanceOf<CouponSoldOutException>()
        }
    }
})