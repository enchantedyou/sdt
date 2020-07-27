package com.ssy.api.utils.system;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * @Description 获取当前请求
     * @Author sunshaoyu
     * @Date 2020/7/6-14:38
     * @return javax.servlet.http.HttpServletRequest
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return CommUtil.isNull(servletRequestAttributes) ? null : servletRequestAttributes.getRequest();
    }

    /**
     * @Description 从session中获取属性
     * @Author sunshaoyu
     * @Date 2020/7/14-18:17
     * @param key
     * @return java.lang.Object
     */
    public static Object getSessionAttribute(String key){
        HttpServletRequest request = getRequest();
        if(CommUtil.isNotNull(request)){
            return request.getSession().getAttribute(key);
        }
        return null;
    }

    /**
     * @Description 从session中获取属性
     * @Author sunshaoyu
     * @Date 2020/7/15-13:13
     * @param key
     * @param clazz
     * @return T
     */
    public static <T> T getSessionAttribute(String key, Class<T> clazz){
        HttpServletRequest request = getRequest();
        if(CommUtil.isNotNull(request)){
            return (T) request.getSession().getAttribute(key);
        }
        return null;
    }
}
