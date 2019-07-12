package com.itcast.dw.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 方法级注解，用于注释会产生并发问题的方法
 * @author liudawei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
	
	String lockedPrefix() default "";//redis锁key的前缀
	long timeOut() default 10;//轮询锁的时间,单位秒
	int expireTime() default 1;//key在redis里存在的时间,单位秒

}
