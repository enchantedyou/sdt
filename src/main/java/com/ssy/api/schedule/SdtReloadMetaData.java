package com.ssy.api.schedule;

import com.ssy.api.factory.odb.MetaDataFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description 重载元数据轮询任务
 * @Author sunshaoyu
 * @Date 2020年08月24日-13:48
 */
@Component
public class SdtReloadMetaData extends MetaDataFactory {

    /** 重载间隔:10分钟 **/
    private static final long RELOAD_INTERVAL = 60 * 10 * 1000;

    @Scheduled(initialDelay = RELOAD_INTERVAL, fixedDelay = RELOAD_INTERVAL)
    public void reload(){
        refreshDynamicDataSource();
        refreshMetaData();
    }
}
