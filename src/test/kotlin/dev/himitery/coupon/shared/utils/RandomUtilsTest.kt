package dev.himitery.coupon.shared.utils

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeUnique

class RandomUtilsTest : FunSpec({

    val randomUtils = RandomUtils()

    test("항상 길이가 10인 랜덤 문자열을 생성한다.") {
        // when
        val randoms = (1..100).map { randomUtils.generate(10) }

        // then
        randoms.shouldBeUnique()
    }
})