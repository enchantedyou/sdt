package com.ssy.api.plugins;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.session.UserInfo;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 权限拦截器
 * @Author sunshaoyu
 * @Date 2020/7/14-12:19
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(SdtConst.USER_INFO);
        if(CommUtil.isNull(userInfo)){
            if(CommUtil.equals(request.getMethod().toUpperCase(), SdtConst.GET_REQUEST)){
                //重定向到登录页
                response.sendRedirect(String.valueOf(request.getAttribute(SdtConst.BASE_PATH)));
            }else{
                //抛出异常
                throw SdtServError.E0009();
            }
        }else{
            //为当前请求线程切换数据源
            DBContextHolder.switchToDataSource(userInfo.getUserDataSource());
        }
        return true;
    }
}
