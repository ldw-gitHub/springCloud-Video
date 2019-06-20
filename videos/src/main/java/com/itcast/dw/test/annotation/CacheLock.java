package com.itcast.dw.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
	
	String lockedPrefix() default "";//redis锁key的前缀
	long timeOut() default 2000;//轮询锁的时间
	int expireTime() default 1000;//key在redis里存在的时间

}
