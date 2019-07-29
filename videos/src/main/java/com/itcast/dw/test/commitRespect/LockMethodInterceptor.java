package com.itcast.dw.test.commitRespect;

import java.lang.reflect.Method;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.itcast.dw.info.BusinessException;

import rx.internal.operators.BufferUntilSubscriber;

@Aspect
@Configuration
public class LockMethodInterceptor {

    @Autowired
    public LockMethodInterceptor(RedisLock redisLock, CacheKeyGenerator cacheKeyGenerator) {
        this.redisLock = redisLock;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    private final RedisLock redisLock;
    private final CacheKeyGenerator cacheKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.itcast.dw.test.commitRespect.CacheLocks)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLocks lock = method.getAnnotation(CacheLocks.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new BusinessException(300,"lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLock.lock(lockKey, value, lock.expire());
            if (!success) {
                throw new BusinessException(300,"重复提交");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new BusinessException(300,"系统异常");
            }
        } finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
        	redisLock.unlock(lockKey);
        }
    }
}
