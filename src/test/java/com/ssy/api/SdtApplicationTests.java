package com.ssy.api;

import com.ssy.api.dao.mapper.ap.ApsAccountingEventMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceControlMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceOutMapper;
import com.ssy.api.dao.mapper.ln.LnaRepaymentScheduleMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.entity.table.edsp.TspServiceIn;
import com.ssy.api.entity.table.local.SdbUser;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.logic.audit.SdSqlAuditExecutor;
import com.ssy.api.logic.higention.SdGitlabReader;
import com.ssy.api.logic.local.SdFlowtranParser;
import com.ssy.api.logic.local.SdJavaParser;
import com.ssy.api.logic.local.SdMessageConvert;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.logic.request.SdIcoreRequest;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfService;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.servicetype.LoanService;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.servicetype.UserService;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = SdtApplication.class)
@Slf4j
class SdtApplicationTests extends MetaDataFactory {

    @Autowired
    private FileLoader fileLoader;
    @Autowired
    private SdtContextConfig contextConfig;
    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private SdGitlabReader gitlab;
    @Autowired
    private TspServiceInMapper tspServiceInMapper;
    @Autowired
    private TspServiceOutMapper tspServiceOutMapper;
    @Autowired
    private TspServiceControlMapper tspServiceControlMapper;
    @Autowired
    private ModuleMapService moduleMapService;
    @Autowired
    private UserService userService;
    @Autowired
    private SdSqlAuditExecutor sqlAuditExecutor;
    @Autowired
    private ApsAccountingEventMapper apsAccountingEventMapper;
    @Autowired
    private LnaRepaymentScheduleMapper lnaRepaymentScheduleMapper;
    @Autowired
    private LoanService loanService;
    @Autowired
    private SdMessageConvert messageConvert;

    static {
        System.setProperty("jasypt.encryptor.password", SdtConst.CONFIG_ENCKEY);
    }

    @Test
    void contextLoads() throws Throwable {
        System.out.println(messageConvert.toUnitTestCode(new String(Files.readAllBytes(Paths.get("C:\\Users\\DELL\\Desktop\\1.json")), SdtConst.DEFAULT_ENCODING)));
    }

    public void fixSharding() throws IOException {
        DBContextHolder.switchToDataSource("ln_fat2");
        StringBuilder builder = new StringBuilder();
        loanService.queryLoanList().forEach(lnaLoan -> {
            long hashValue = loanService.getGroupHashValue(20, lnaLoan.getLoanNo()) - 1L;
            builder.append("delete from lna_repayment_schedule_" + hashValue+" where loan_no = '").append(lnaLoan.getLoanNo()).append("';\r\n");
            builder.append("insert into lna_repayment_schedule_" + hashValue+" select * from lna_repayment_schedule where loan_no = '" + lnaLoan.getLoanNo()).append("';\r\n");
        });
        builder.append("commit;");
        fileLoader.saveFile(builder.toString(), "C:\\Users\\DELL\\Desktop\\1.sql");
    }

    public void reversalService(){
        List<IntfService> serviceList = new ArrayList<>();

        //服务接入表
        tspServiceInMapper.selectAll_odb1("rpc").forEach(tspServiceIn -> {
            if("D".equals(tspServiceIn.getTransactionMode()) && "T".equals(tspServiceIn.getServiceCategory())){
                Flowtran flowtran = SdFlowtranParser.load(tspServiceIn.getInnerServiceCode());
                serviceList.addAll(flowtran.getServiceList());
                log.info("交易{}的服务数:{},服务列表:{}", tspServiceIn.getInnerServiceCode(), flowtran.getServiceList().size(), flowtran.getServiceList());
            }
        });

        serviceList.forEach(service -> {
            TspServiceIn tspServiceIn = tspServiceInMapper.selectOne_odb1(CommUtil.nvl(service.getId(), service.getServiceName()));
            if(CommUtil.isNotNull(tspServiceIn)){
                tspServiceIn.setTransactionMode("D");
                tspServiceInMapper.updateByPrimaryKey(tspServiceIn);
            }
        });

        //服务控制表
        tspServiceControlMapper.selectAll_odb1().forEach(tspServiceControl -> {
            String cancelService = tspServiceControl.getCancelService();
            if(CommUtil.isNotNull(cancelService) && cancelService.toLowerCase().contains("cancel")){
                tspServiceControl.setServiceTransactionMode("Required");
                tspServiceControl.setServiceType("try");
                tspServiceControlMapper.updateByPrimaryKey(tspServiceControl);
            }
        });
    }

    public void dockerRest(){
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
            tspServiceInMapper.insert(tspServiceIn);
        });
        //移除rpc协议的服务接入信息
        tspServiceInMapper.selectAll_odb1("rpc").forEach(tspServiceIn -> {
            tspServiceInMapper.deleteByPrimaryKey(tspServiceIn.getSystemCode(),tspServiceIn.getSubSystemCode(),tspServiceIn.getOutServiceCode());
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
            String moduleId = moduleMapping.getModuleId();
            if(CommUtil.equals(moduleMapping.getSubSystemCode(), subSystemCode)){
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
