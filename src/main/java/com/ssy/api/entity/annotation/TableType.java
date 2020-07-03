package com.ssy.api.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 表类型注解,标注表名和表描述,用在dao层的mapper类
 * @Author sunshaoyu
 * @Date 2020/7/3-11:17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableType {

    /** 表名 **/
    public String name();

    /** 表描述 **/
    public String desc();
}
