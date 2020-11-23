package com.ssy.api.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 禁用防xxs跨站脚本攻击字符转换
 * @Author sunshaoyu
 * @Date 2020/10/23-17:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DisableXss {
}
