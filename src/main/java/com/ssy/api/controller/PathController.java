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

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/exception")
    public String error(){
        return "error";
    }

    @RequestMapping("/menu1000")
    public String menu1000(){
        return "component/menu1000";
    }

    @RequestMapping("/menu1001")
    public String menu1001(){
        return "component/menu1001";
    }

    @RequestMapping("/menu1002")
    public String menu1002(){
        return "component/menu1002";
    }

    @RequestMapping("/menu1003")
    public String menu1003(){
        return "component/menu1003";
    }

    @RequestMapping("/menu1004")
    public String menu1004(){
        return "component/menu1004";
    }

    @RequestMapping("/menu1005")
    public String menu1005(){
        return "component/menu1005";
    }

    @RequestMapping("/menu1006")
    public String menu1006(){
        return "component/menu1006";
    }

    @RequestMapping("/menu1007")
    public String menu1007(){
        return "component/menu1007";
    }

    @RequestMapping("/menu2000")
    public String menu2000(){
        return "component/menu2000";
    }

    @RequestMapping("/menu2001")
    public String menu2001(){
        return "component/menu2001";
    }

    @RequestMapping("/menu2002")
    public String menu2002(){
        return "component/menu2002";
    }

    @RequestMapping("/menu3000")
    public String menu3000(){
        return "component/menu3000";
    }

    @RequestMapping("/menu3001")
    public String menu3001(){
        return "component/menu3001";
    }

    @RequestMapping("/menu3002")
    public String menu3002(){
        return "component/menu3002";
    }

    @RequestMapping("/menu4000")
    public String menu4000(){
        return "component/menu4000";
    }

    @RequestMapping("/menu5000")
    public String menu5000(){
        return "component/menu5000";
    }
}
