package dev.himitery.coupon.shared.aop.distributed_lock

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.redisson.api.RedissonClient
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.util.concurrent.TimeUnit

@Order(1)
@Aspect
@Component
class DistributedLockAspect(private val redissonClient: RedissonClient) {

    @Around("@annotation(distributedLock)")
    fun around(joinPoint: ProceedingJoinPoint, distributedLock: DistributedLock): Any? {
        val lock = redissonClient.getLock(distributedLock.key)
        var lastException: Exception? = null

        for (attempt in 1..distributedLock.retryAttempts) {
            try {
                if (lock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, TimeUnit.MILLISECONDS)) {
                    try {
                        return joinPoint.proceed()
                    } finally {
                        if (lock.isHeldByCurrentThread) {
                            lock.unlock()
                        }
                    }
                } else {
                    Thread.sleep(distributedLock.retryDelay)
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                lastException = e
                break
            } catch (e: Exception) {
                if (distributedLock.noRetryFor.any { it.java.isInstance(e) }) {
                    throw e
                }
                lastException = e
                Thread.sleep(distributedLock.retryDelay)
            }
        }

        val recoveryMethod = findRecoveryMethod(joinPoint.target, joinPoint.args.size)
        if (recoveryMethod != null) {
            recoveryMethod.isAccessible = true
            return recoveryMethod.invoke(
                joinPoint.target,
                *arrayOf<Any?>(lastException),
                *joinPoint.args,
            )
        }

        throw InterruptedException()
    }

    private fun findRecoveryMethod(target: Any, originalArgsCount: Int): Method? {
        return target::class.java.declaredMethods.find {
            it.isAnnotationPresent(Recover::class.java) && it.parameterCount == originalArgsCount + 1
        }
    }
}
