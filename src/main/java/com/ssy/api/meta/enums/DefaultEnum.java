package com.ssy.api.meta.enums;

/**
 * @Description 默认的枚举类型
 * @Author sunshaoyu
 * @Date 2020/5/27-13:12
 */
public interface DefaultEnum<T>{

    public abstract String getId();

    public abstract T getValue();

    public abstract String getLongName();
}
