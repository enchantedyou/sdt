package com.ssy.api.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 用于控制层的入参,将传入后的参数解密后注入控制层
 * @Author sunshaoyu
 * @Date 2020年07月11日-12:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface EncryptedArgument {
}
