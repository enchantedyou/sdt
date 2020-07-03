package com.ssy.api.plugins;

import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.utils.BizUtil;
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
public class SdtApplicationStartupLoader extends MetaDataFactory implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /** 初始化元数据 **/
        StopWatch s = BizUtil.startStopWatch();
        //初始化接口文档文件
        loadIntfWordFileMap();
        //初始化限制类型
        loadRestrictionTypeMap();

        //初始化复合类型
        loadComplexTypeMap();
        //初始化项目字典
        loadDictMap();
        //初始化表模型
        loadTableTypeMap();

        /** 初始化动态数据源 **/
        loadDynamicDataSource();
        BizUtil.stoptStopWatch(s, "Load all metadata");
    }
}
