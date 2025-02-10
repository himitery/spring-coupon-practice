package dev.himitery.coupon.shared.utils

import org.springframework.stereotype.Component
import java.security.SecureRandom

@Component
class RandomUtils {
    private val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    private val random = SecureRandom()

    fun generate(length: Int): String {
        return (1..length)
            .map { charset[random.nextInt(charset.length)] }
            .joinToString("")
    }
}