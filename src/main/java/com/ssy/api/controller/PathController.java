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
    public String login(){
        return "login";
    }

    /**
     * @Description 跳转至主页
     * @Author sunshaoyu
     * @Date 2020/7/15-13:18
     * @return java.lang.String
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * @Description 跳转至错误页
     * @Author sunshaoyu
     * @Date 2020/7/27-10:33
     * @return java.lang.String
     */
    @RequestMapping("/exception")
    public String error(){
        return "error";
    }

    /**
     * @Description 返回menu1000视图
     * @Author sunshaoyu
     * @Date 2020/7/16-14:24
     * @return java.lang.String
     */
    @RequestMapping("/menu1000")
    public String menu1000(){
        return "component/menu1000";
    }

    /**
     * @Description 返回menu1001视图
     * @Author sunshaoyu
     * @Date 2020/7/23-16:25
     * @return java.lang.String
     */
    @RequestMapping("/menu1001")
    public String menu1001(){
        return "component/menu1001";
    }

    /**
     * @Description 返回menu2000视图
     * @Author sunshaoyu
     * @Date 2020/7/17-15:19
     * @return java.lang.String
     */
    @RequestMapping("/menu2000")
    public String menu2000(){
        return "component/menu2000";
    }
}
