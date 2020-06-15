package com.ssy.api.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description spring上下文工具类
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:49
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @Description 获取spring上下文中的Bean
     * @Author sunshaoyu
     * @Date 2020/6/15-14:51
     * @param beanName  实体名称
     * @return java.lang.Object
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * @Description 获取spring上下文中的Bean
     * @Author sunshaoyu
     * @Date 2020/6/15-14:52
     * @param beanType 实体类型
     * @return java.lang.Object
     */
    public static <T> T getBean(Class<T> beanType) {
        return applicationContext.getBean(beanType);
    }
}
