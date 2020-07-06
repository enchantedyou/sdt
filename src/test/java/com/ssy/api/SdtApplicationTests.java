package com.ssy.api;

import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.plugins.SdtBatchScanner;
import com.ssy.api.servicetype.AppDateService;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.servicetype.ModulePriortyService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SdtApplication.class)
class SdtApplicationTests {

    protected static final Logger logger = LoggerFactory.getLogger(SdtApplicationTests.class);

    @Autowired
    private ModulePriortyService modulePriortyService;
    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private AppDateService appDateService;
    @Autowired
    private TspServiceInMapper tspServiceInMapper;

    @Test
    void contextLoads() throws Exception {

        DBContextHolder.switchToDataSource("ln_dev");

        System.out.println(appDateService.queryCurrentDate());
        System.out.println("字典优先级数量:"+modulePriortyService.queryEffectDictPriortyList().size());
        System.out.println("服务接入表:"+tspServiceInMapper.selectByPrimaryKey("102", "1021", "326020").hashCode());
        System.out.println("动态数据源数量:"+dataSourceService.queryDataSourceList().size());

        for(int i = 0;i < 10;i++){
            new Thread(() -> System.out.println(SdtBatchScanner.getInstance().hashCode())).start();
        }
    }
}
