package com.ssy.api;

import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.defaults.TableType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SdtApplication.class)
class SdtApplicationTests {

    protected static final Logger logger = LoggerFactory.getLogger(SdtApplicationTests.class);

    @Test
    void contextLoads() {
        TableType table = OdbFactory.searchTable("lna_loan");
        System.out.println(table);
        for(String key: table.getFieldMap().keySet()){
            System.out.println(table.getFieldMap().get(key));
        }
    }

}
