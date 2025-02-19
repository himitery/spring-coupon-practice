package dev.himitery.coupon.shared.aop.distributed_lock

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Inherited
annotation class DistributedLock(
    val key: String,
    val waitTime: Long = 5000,
    val leaseTime: Long = 10000,
    val retryAttempts: Int = 3,
    val retryDelay: Long = 100,
    val noRetryFor: Array<KClass<out Throwable>> = []
)
