package com.ssy.api;

import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.logic.local.SdPTEJsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SdtApplication.class)
@Slf4j
class SdtApplicationTests {

    protected static final Logger logger = LoggerFactory.getLogger(SdtApplicationTests.class);

    @Test
    void contextLoads() throws Throwable {
        SdBuildPTE buildPTE = new SdBuildPTE();
        buildPTE.setFlowtranId("ln6020");
        buildPTE.setPteModule(E_PTEMODULE.dynamicTabs);
        System.out.println(SdPTEJsonParser.buildPTEJson(buildPTE));
    }
}
