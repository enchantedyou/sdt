package com.ssy.api;

import com.ssy.api.factory.odb.OdbFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SdtApplication.class)
class SdtApplicationTests {

    protected static final Logger logger = LoggerFactory.getLogger(SdtApplicationTests.class);

    @Test
    void contextLoads() {
        System.out.println(OdbFactory.searchDict("trxn_seq"));
    }

}
