package com.ssy.api.utils.http;

import com.ssy.api.utils.system.CommUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月13日-14:39
 */
public class HttpServletUtil {

    /**
     * @Description 获取请求的ip地址
     * @Author sunshaoyu
     * @Date 2020/7/13-14:36
     * @param request
     * @return java.lang.String
     */
    public static String getRemoteHostAddr(HttpServletRequest request) {
        if (CommUtil.isNull(request.getHeader("x-forwarded-for"))) {
            return request.getRemoteAddr();
        }else{
            return request.getHeader("x-forwarded-for");
        }
    }
}
