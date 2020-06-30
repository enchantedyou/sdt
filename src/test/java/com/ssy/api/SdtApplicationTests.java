package com.ssy.api;

import com.alibaba.fastjson.JSONObject;
import com.ssy.api.entity.sump.component.PTEComponent;
import com.ssy.api.serv.SdPTEJsonParser;
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

    @Test
    void contextLoads() throws Exception {
        PTEComponent pte = SdPTEJsonParser.searchOne("ln_normal_open_tab.json");
        System.out.println(JSONObject.toJSONString(pte));
    }

}
