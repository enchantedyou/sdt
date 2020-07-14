package com.ssy.api.entity.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ssy.api.plugins.AuthorityInterceptor;
import com.ssy.api.plugins.SdtArgumentResolver;
import com.ssy.api.plugins.SysContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description mvc配置类
 * @Author sunshaoyu
 * @Date 2020年07月11日-12:29
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SdtArgumentResolver sdtArgumentResolver;
    @Autowired
    private SysContextInterceptor sysContextInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    /**
     * @Description 添加控制层参数处理器
     * @Author sunshaoyu
     * @Date 2020/7/11-16:29
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sdtArgumentResolver);
    }

    /**
     * @Description 添加拦截器
     * @Author sunshaoyu
     * @Date 2020/7/11-16:30
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //系统拦截器,用于初始化流水和基础路径
        registry.addInterceptor(sysContextInterceptor).addPathPatterns("/**");
        //权限拦截器
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/select/*");
    }

    /**
     * @Description 自定义消息转换器
     * @Author sunshaoyu
     * @Date 2020/7/14-15:06
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, converter);
    }
}
