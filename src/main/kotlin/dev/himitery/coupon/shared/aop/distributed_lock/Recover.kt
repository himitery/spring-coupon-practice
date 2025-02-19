package dev.himitery.coupon.shared.aop.distributed_lock

import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Inherited
annotation class Recover
