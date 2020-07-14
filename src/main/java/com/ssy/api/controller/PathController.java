package com.ssy.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 路径控制器
 * @Author sunshaoyu
 * @Date 2020/7/8-20:11
 */
@Controller
public class PathController {

    /**
     * @Description 跳转至登录页
     * @Author sunshaoyu
     * @Date 2020/7/8-20:12
     * @return java.lang.String
     */
    @RequestMapping("/")
    public String index(){
        return "login";
    }
}
