package com.ssy.api;

import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceOutMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.entity.lang.TwoTuple;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.logic.higention.SdGitlabReader;
import com.ssy.api.logic.local.SdJavaParser;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.logic.request.SdIcoreRequest;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.JDBCHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = SdtApplication.class)
@Slf4j
class SdtApplicationTests extends MetaDataFactory {

    @Autowired
    private FileLoader fileLoader;
    @Autowired
    private SdGitlabReader gitlab;
    @Autowired
    private TspServiceInMapper tspServiceInMapper;
    @Autowired
    private TspServiceOutMapper tspServiceOutMapper;
    @Autowired
    private ModuleMapService moduleMapService;

    @Autowired
    private JDBCHelper jdbcHelper;

    @Test
    void contextLoads() throws Throwable {
        Map<String, TwoTuple<String, String>> map = new HashMap<>();
        map.put("limit_type", new TwoTuple<>("引用", "类型"));
        OdbFactory.metaDataNormalization("cl",map);
    }

    public void dockerRest(){
        DBContextHolder.switchToDataSource("ln_dev");
        List<SdpModuleMapping> moduleMappingList = moduleMapService.queryAllModuleList();
        Map<String, String> rollbackMap = new HashMap<>();

        //服务接入表
        tspServiceInMapper.selectAll_odb1("rpc").forEach(tspServiceIn -> {
            tspServiceIn.setProtocolId("rest");
            if(CommUtil.equals(tspServiceIn.getServiceCategory(), "T")){
                tspServiceIn.setOutServiceCode("/"+tspServiceIn.getInnerServiceCode());
            }else if(CommUtil.equals(tspServiceIn.getServiceCategory(), "S")){
                String[] outServiceCode = tspServiceIn.getOutServiceCode().split("\\.");
                String newOutServiceCode = "/"+outServiceCode[0].substring(0,1).toLowerCase() + outServiceCode[0].substring(1) + "/" + outServiceCode[1];

                if(outServiceCode[0].toLowerCase().contains("txc") || outServiceCode[0].toLowerCase().contains("msreversal")){
                    newOutServiceCode = "/" + tspServiceIn.getInnerServiceImplCode().substring(0,1).toLowerCase() + tspServiceIn.getInnerServiceImplCode().substring(1) + newOutServiceCode;
                    rollbackMap.put(tspServiceIn.getInnerServiceCode(), newOutServiceCode);
                }
                tspServiceIn.setOutServiceCode(newOutServiceCode);
            }
            if(tspServiceIn.getOutServiceCode().length() > 50){
                System.out.println(tspServiceIn.getOutServiceCode());
            }
            //tspServiceInMapper.insert(tspServiceIn);
        });

        //服务接出表
        tspServiceOutMapper.selectAll_odb1("remote_rpc").forEach(tspServiceOut -> {
            tspServiceOut.setProtocolId("rest");
            tspServiceOut.setOutServiceApp(CommUtil.nvl(getServiceOutApp(moduleMappingList, tspServiceOut.getOutServiceApp()), tspServiceOut.getOutServiceApp()));
            String[] outServiceCode = tspServiceOut.getOutServiceCode().split("\\.");
            if(outServiceCode.length >= 2){
                String newOutServiceCode = "/"+outServiceCode[0].substring(0,1).toLowerCase() + outServiceCode[0].substring(1) + "/" + outServiceCode[1];
                String rollbackService = rollbackMap.get(tspServiceOut.getInnerServiceCode());
                if(CommUtil.isNotNull(rollbackService)){
                    newOutServiceCode = rollbackService;
                }
                tspServiceOut.setOutServiceCode(newOutServiceCode);
                tspServiceOut.setOutServiceGroup("POST");
                tspServiceOutMapper.updateByPrimaryKey(tspServiceOut);
            }
        });
    }

    private String getServiceOutApp(List<SdpModuleMapping> moduleMappingList, String subSystemCode){
        for(SdpModuleMapping moduleMapping : moduleMappingList){
            if(CommUtil.equals(moduleMapping.getSubSystemCode(), subSystemCode)){
                String moduleId = moduleMapping.getModuleId();
                if(CommUtil.equals("cf", moduleId)){
                    moduleId = "us";
                }
                return moduleId.toLowerCase() + "-onl";
            }
        }
        return null;
    }

    void doRequest312020(int concurrentNum) throws IOException {
        String url = "http://10.22.63.72:9009/gateway";
        String body = fileLoader.loadAsString(new File("C:\\Users\\DELL\\Desktop\\312020.json"), SdtConst.DEFAULT_ENCODING);
        SdIcoreRequest.doRequest(url, body, concurrentNum);
    }

    void PTE(){
        SdBuildPTE buildPTE = new SdBuildPTE();
        buildPTE.setFlowtranId("ln6020");
        buildPTE.setPteModule(E_PTEMODULE.dynamicTabs);
        System.out.println(SdPTEJsonParser.buildPTEJson(buildPTE));
    }

    void javaParse() throws Exception {
        String path = "D:\\Sunline\\sunlineWorkspace\\icore3.0\\ln-busi\\ln-serv\\src\\main\\java\\cn\\sunline\\icore\\ln\\serv\\serviceimpl\\loan\\SrvIoLnLoanOpenImpl.java";
        List<String> fieldNameList = SdJavaParser.searchMethodCalls(new File(path), "loanNormalOpenAndCheck", SdJavaParser.MANDATORY_STMT);
        for(String name : fieldNameList){
            System.out.println(name);
        }
    }
}
