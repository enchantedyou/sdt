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
     * @Description 返回menu1002视图
     * @Author sunshaoyu
     * @Date 2020/8/6-16:44
     * @return java.lang.String
     */
    @RequestMapping("/menu1002")
    public String menu1002(){
        return "component/menu1002";
    }

    /**
     * @Description 返回menu1003视图
     * @Author sunshaoyu
     * @Date 2020/8/11-14:30
     * @return java.lang.String
     */
    @RequestMapping("/menu1003")
    public String menu1003(){
        return "component/menu1003";
    }

    /**
     * @Description 返回menu1004视图
     * @Author sunshaoyu
     * @Date 2020/9/7-11:02
     * @return java.lang.String
     */
    @RequestMapping("/menu1004")
    public String menu1004(){
        return "component/menu1004";
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

    /**
     * @Description 返回menu2001视图
     * @Author sunshaoyu
     * @Date 2020/8/6-16:57
     * @return java.lang.String
     */
    @RequestMapping("/menu2001")
    public String menu2001(){
        return "component/menu2001";
    }

    /**
     * @Description 返回menu2002视图
     * @Author sunshaoyu
     * @Date 2020/8/28-15:35
     * @return java.lang.String
     */
    @RequestMapping("/menu2002")
    public String menu2002(){
        return "component/menu2002";
    }

    /**
     * @Description 返回menu3000视图
     * @Author sunshaoyu
     * @Date 2020/7/31-14:31
     * @return java.lang.String
     */
    @RequestMapping("/menu3000")
    public String menu3000(){
        return "component/menu3000";
    }

    /**
     * @Description 返回menu3001视图
     * @Author sunshaoyu
     * @Date 2020/8/5-14:49
     * @return java.lang.String
     */
    @RequestMapping("/menu3001")
    public String menu3001(){
        return "component/menu3001";
    }

    /**
     * @Description 返回menu4000视图
     * @Author sunshaoyu
     * @Date 2020/8/12-9:45
     * @return java.lang.String
     */
    @RequestMapping("/menu4000")
    public String menu4000(){
        return "component/menu4000";
    }
}
