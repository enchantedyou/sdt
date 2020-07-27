package com.ssy.api.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 交易事件注解
 * @Author sunshaoyu
 * @Date 2020/7/11-14:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrxnEvent {

    /**
     * @Description 为空时不登记请求响应报文表,只按统一的格式返回
     * @Author sunshaoyu
     * @Date 2020/7/15-11:39
     */
    public String value() default "";
}
