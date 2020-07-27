package com.ssy.api.controller;

import com.ssy.api.entity.annotation.EncryptedArgument;
import com.ssy.api.entity.annotation.TrxnEvent;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.entity.type.local.Empty;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.logic.local.SdFlowtranParser;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfFields;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.utils.system.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 远程下拉控制层
 * @Author sunshaoyu
 * @Date 2020年07月11日-17:11
 */
@Slf4j
@RestController
@RequestMapping("/select")
public class RemoteSelectController {

    @Autowired
    private DataSourceService dataSourceService;

    /**
     * @Description 数据源下拉列表
     * @Author sunshaoyu
     * @Date 2020/7/11-17:05
     * @param empty
     * @return java.util.List<com.ssy.api.entity.table.local.SdpDatasource>
     */
    @TrxnEvent
    @PostMapping("/dataSource")
    public Object queryDataSourceList(@EncryptedArgument Empty empty){
        return dataSourceService.queryDataSourceList();
    }

    /**
     * @Description PTE模板下拉列表
     * @Author sunshaoyu
     * @Date 2020/7/23-16:23
     * @param empty
     * @return java.lang.Object
     */
    @TrxnEvent
    @PostMapping("/pteModule")
    public Object queryPTEModuleList(@EncryptedArgument Empty empty){
        return E_PTEMODULE.values();
    }

    /**
     * @Description 列表名称下拉列表
     * @Author sunshaoyu
     * @Date 2020/7/27-16:52
     * @param buildPTE
     * @return java.lang.Object
     */
    @TrxnEvent
    @PostMapping("/listName")
    public Object queryListNameList(@EncryptedArgument SdBuildPTE buildPTE){
        BizUtil.fieldNotNull(buildPTE.getFlowtranId(), SdtDict.A.flowtran_id.getId(), SdtDict.A.flowtran_id.getLongName());
        BizUtil.fieldNotNull(buildPTE.getPteModule(), SdtDict.A.pte_module.getId(), SdtDict.A.pte_module.getLongName());
        Flowtran flowtran = SdFlowtranParser.load(buildPTE.getFlowtranId());
        List<IntfFields> nameList = new ArrayList<>();

        nameList.addAll(flowtran.getInput().getFieldsList());
        nameList.addAll(flowtran.getOutput().getFieldsList());
        return nameList;
    }
}
