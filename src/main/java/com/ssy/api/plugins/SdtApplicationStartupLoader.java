package com.ssy.api.plugins;

import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.utils.system.BizUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @Description 元数据启动加载器,在服务启动时将核心项目的元数据加载到缓存
 * @Author sunshaoyu
 * @Date 2020年06月13日-18:08
 */
@Component
@Order(value = 1)
public class SdtApplicationStartupLoader implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StopWatch s = BizUtil.startStopWatch();
        /** 初始化元数据 **/
        OdbFactory.loadMetaDataInitially();
        /** 初始化动态数据源 **/
        OdbFactory.loadDynamicDataSource();
        BizUtil.stopStopWatch(s, "Load all metadata");
    }
}
