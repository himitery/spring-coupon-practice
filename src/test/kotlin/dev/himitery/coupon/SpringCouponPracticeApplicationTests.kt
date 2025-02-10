package dev.himitery.coupon

import dev.himitery.coupon.shared.config.TestcontainersConfig
import dev.himitery.coupon.shared.property.TestProperty
import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(TestcontainersConfig::class)
@EnableConfigurationProperties(TestProperty::class)
class SpringCouponPracticeApplicationTests {

    @Test
    fun contextLoads() {
    }
}
