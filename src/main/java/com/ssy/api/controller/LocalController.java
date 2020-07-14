package com.ssy.api.controller;

import com.ssy.api.entity.annotation.EncryptedArgument;
import com.ssy.api.entity.annotation.TrxnEvent;
import com.ssy.api.entity.type.local.SdLoginIn;
import com.ssy.api.servicetype.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月11日-13:57
 */
@Slf4j
@RestController
@RequestMapping("/local")
public class LocalController {

    @Autowired
    private UserService userService;

    /**
     * @Description 用户登录
     * @Author sunshaoyu
     * @Date 2020/7/11-17:03
     * @param loginIn
     * @return com.ssy.api.entity.type.local.SdLoginOut
     */
    @TrxnEvent("login")
    @PostMapping("/login")
    public Object login(@EncryptedArgument SdLoginIn loginIn){
        return userService.login(loginIn);
    }
}
