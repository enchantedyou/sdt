package com.ssy.api.logic.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.entity.lang.ResponseData;
import com.ssy.api.exception.SdtException;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.http.HttpUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description 核心请求
 * @Author sunshaoyu
 * @Date 2020年08月12日-15:02
 */
@Component
@Slf4j
public class SdIcoreRequest {

    /**
     * @Description 向核心发起请求
     * @Author sunshaoyu
     * @Date 2020/8/12-15:51
     * @param url
     * @param body
     * @param concurrentCount
     */
    public static void doRequest(String url, String body, int concurrentCount){
        JSONObject jsonObject = JSON.parseObject(body);
        String serviceCode = jsonObject.getJSONObject("sys").getString("servicecode");
        String subSystemId = jsonObject.getJSONObject("comm_req").getString("initiator_system") + "1";

        CommUtil.concurrentExecute(concurrentCount, 0, () -> {
            String responseBody = HttpUtil.doPost(url, SdtBusiUtil.getIcoreHeaders(subSystemId, serviceCode), body);
            if(!CommUtil.equals(JSON.parseObject(responseBody).getJSONObject("sys").getString("erorcd"), ErrCodeDef.SUCCESS)){
                throw new SdtException("Core transaction request failed");
            }
            return responseBody;
        });
    }
}
