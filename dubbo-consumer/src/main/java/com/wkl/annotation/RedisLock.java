package com.wkl.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface RedisLock {
	String key();
	String value() default "";
	long expire() default 60; //失效时间
}
