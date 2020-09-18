package com.ssy.api;

import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceOutMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.entity.enums.E_STRGENTYPE;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.logic.audit.SdSqlAuditExecutor;
import com.ssy.api.logic.higention.SdGitlabReader;
import com.ssy.api.logic.local.SdJavaParser;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.logic.request.SdIcoreRequest;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.parse.ExcelParser;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
    private SdSqlAuditExecutor sqlAuditExecutor;

    @Test
    void contextLoads() throws Throwable {
    }

    /**
     * @Description 白名单
     * @Author sunshaoyu
     * @Date 2020/9/12-16:36
     */
    public static void genWhiteList(int size, OutputStream outputStream){
        try{
            Workbook workbook = ExcelParser.getWorkbook(ExcelParser.class.getResource("/templates/excel/white_list_import_template.xlsx").getPath());
            Sheet sheet = workbook.getSheetAt(0);
            for(int i = 1;i <= size;i++){
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue("Alibaba");
                row.createCell(1).setCellValue("1");
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue(CommUtil.randStr(10, E_STRGENTYPE.LOWER));
                row.createCell(4).setCellValue("M");
                row.createCell(5).setCellValue("19900101");
                row.createCell(6).setCellValue(CommUtil.randStr(11, E_STRGENTYPE.NUMBER));
                row.createCell(7).setCellValue("");
                row.createCell(8).setCellValue(CommUtil.randStr(10, E_STRGENTYPE.NUMBER) + "@qq.com");
                row.createCell(9).setCellValue("90000");
                row.createCell(10).setCellValue("IDR");
                row.createCell(11).setCellValue("12M");
                row.createCell(12).setCellValue("Y");
                row.createCell(13).setCellValue("20191110");
                row.createCell(14).setCellValue("20191231");
                row.createCell(15).setCellValue("");
                row.createCell(16).setCellValue("");
                row.createCell(17).setCellValue("");
            }
            workbook.write(outputStream);
        }catch (Exception e){
            throw new SdtException("Failed to generate whitelist", e);
        }
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
