package dev.himitery.coupon

import dev.himitery.coupon.shared.config.TestcontainersConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestcontainersConfig::class)
class SpringCouponPracticeApplicationTests {

    @Test
    fun contextLoads() {
    }
}
