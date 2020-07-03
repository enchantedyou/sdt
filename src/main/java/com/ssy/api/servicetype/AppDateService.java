package com.ssy.api.servicetype;

/**
 * @Description 交易日期相关服务接口
 * @Author sunshaoyu
 * @Date 2020/7/2-13:46
 */
public interface AppDateService {

    public String queryCurrentDate();

    public void resetCurrentDate(String trxnDate);

    public void resetCurrentDate();
}
