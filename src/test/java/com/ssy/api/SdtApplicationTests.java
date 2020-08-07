package com.ssy.api;

import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.logic.audit.SdSqlAuditExecutor;
import com.ssy.api.logic.higention.SdGitlabReader;
import com.ssy.api.logic.higention.SdNexus;
import com.ssy.api.logic.local.SdJavaParser;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.utils.parse.ExcelParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@SpringBootTest(classes = SdtApplication.class)
@Slf4j
class SdtApplicationTests extends MetaDataFactory {

    @Autowired
    private FileLoader fileLoader;
    @Autowired
    private SdGitlabReader gitlab;
    @Autowired
    private SdSqlAuditExecutor sqlAuditExecutor;

    @Test
    void contextLoads() throws Throwable {
        ExcelParser.genInterfaceDoc("ln6020", new FileOutputStream(new File("C:\\Users\\DELL\\Desktop\\1.xlsx")));
    }

    void PTE(){
        SdBuildPTE buildPTE = new SdBuildPTE();
        buildPTE.setFlowtranId("ln6020");
        buildPTE.setPteModule(E_PTEMODULE.dynamicTabs);
        System.out.println(SdPTEJsonParser.buildPTEJson(buildPTE));
    }

    void javaParse() throws Exception {
        String path = "D:\\Sunline\\sunlineWorkspace\\icore3.0\\ln-busi\\ln-serv\\src\\main\\java\\cn\\sunline\\icore\\ln\\serv\\serviceimpl\\loan\\SrvIoLnLoanOpenImpl.java";
        List<String> fieldNameList = SdJavaParser.searchMandatoryFields(new File(path), "loanNormalOpenAndCheck");
        for(String name : fieldNameList){
            System.out.println(name);
        }
    }
}
