package com.ssy.api.plugins;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 系统上下文拦截器
 * @Author sunshaoyu
 * @Date 2020年07月11日-16:25
 */
@Component
public class SysContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(CommUtil.equals(request.getMethod().toUpperCase(), SdtConst.POST_REQUEST)){
            BizUtil.initRunEnvs();
        }
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath()+"/";
        request.setAttribute(SdtConst.BASE_PATH, basePath);
        return true;
    }
}
