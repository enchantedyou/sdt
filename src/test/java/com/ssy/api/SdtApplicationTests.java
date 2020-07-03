package com.ssy.api;

import com.alibaba.fastjson.JSONObject;
import com.ssy.api.dao.mapper.ap.AppDateMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtTable;
import com.ssy.api.entity.sump.component.PTEComponent;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.serv.SdPTEJsonParser;
import com.ssy.api.servicetype.AppDateService;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.servicetype.ModulePriortyService;
import com.ssy.api.utils.CommUtil;
import com.sun.xml.internal.txw2.output.XmlSerializer;
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
    }
}
