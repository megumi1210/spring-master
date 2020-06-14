package com.example.annotation;

import java.lang.annotation.*;


/**
 *  aop 测试注解拦截
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String name();
}
