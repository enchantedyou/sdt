package com.ssy.api.plugins;

import com.ssy.api.utils.system.SeqUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Description 程序结束时的钩子
 * @Author sunshaoyu
 * @Date 2020年09月20日-22:05
 */
@Slf4j
@Component
public class SdtShutdownHook implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        SeqUtil.syncCachedSequence();
    }
}
