package com.ssy.api.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 允许配置dao层select方法的最后一个入参(Boolean类型),
 * 如果查询结果为空且最后一个入参为true,则抛出异常,如果最后一个为boolean类型的入参不存在,则该注解无效,
 * 并且必须和@TableType注解搭配使用
 * @Author sunshaoyu
 * @Date 2020/7/2-19:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableNotNull {
}
