package com.ssy.api;

import com.alibaba.fastjson.JSON;
import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.dao.mapper.local.SdbUserMapper;
import com.ssy.api.entity.lang.ResponseData;
import com.ssy.api.entity.table.local.SdbUser;
import com.ssy.api.logic.batch.SdEODBatchHelper;
import com.ssy.api.servicetype.AppDateService;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.servicetype.ModulePriortyService;
import com.ssy.api.utils.security.AesEnDecrypt;
import com.ssy.api.utils.security.Md5Encrypt;
import com.ssy.api.utils.system.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SdtApplication.class)
@Slf4j
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
    @Autowired
    private SdEODBatchHelper batchHelper;
    @Autowired
    private SdbUserMapper userMapper;


    @Test
    void contextLoads() throws Exception {
        /*SdCallBatchIn callBatchIn = new SdCallBatchIn();
        callBatchIn.setBatchCallMode(E_BATCHCALLMODE.DAYS);
        callBatchIn.setAssignDays(1);
        callBatchIn.setFlowGroup("dev");
        callBatchIn.setBatchRunNo("CoreEOD_1594110765867");
        batchHelper.asyncCallBatch(callBatchIn);*/

        /*String[] dbs = {"ln_dev","dp_dev","cm_dev"};
        for(String db : dbs){
            DBContextHolder.switchToDataSource(db);
            appDateService.resetCurrentDate("20191107");
        }*/
        SdbUser user = userMapper.selectByPrimaryKey("admin");
        user.setLoginIp("127.0.0.1");
        user.setLoginTime(BizUtil.getCurSysTime());
        userMapper.updateByPrimaryKey(user);
    }
}
