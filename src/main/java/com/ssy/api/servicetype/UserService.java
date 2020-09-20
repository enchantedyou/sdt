package com.ssy.api.servicetype;

import com.ssy.api.entity.type.local.SdLoginIn;
import com.ssy.api.entity.type.local.SdLoginOut;

/**
 * @Description 用户相关服务接口
 * @Author sunshaoyu
 * @Date 2020/7/14-11:26
 */
public interface UserService {

    /**
     * @Description 用户登录
     * @Author sunshaoyu
     * @Date 2020/7/14-11:27
     * @param loginIn
     * @return com.ssy.api.entity.type.local.SdLoginOut
     */
    public SdLoginOut login(SdLoginIn loginIn);

    /**
     * @Description 注销登录
     * @Author sunshaoyu
     * @Date 2020/9/19-23:32
     */
    public void logout();
}
