package com.ssy.api.controller;

import com.ssy.api.entity.annotation.EncryptedArgument;
import com.ssy.api.entity.annotation.TrxnEvent;
import com.ssy.api.entity.type.local.Empty;
import com.ssy.api.servicetype.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 远程下拉控制器
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
     * @Description
     * @Author sunshaoyu
     * @Date 2020/7/11-17:05
     * @param empty
     * @return java.util.List<com.ssy.api.entity.table.local.SdpDatasource>
     */
    @TrxnEvent("query data source select list")
    @PostMapping("/dataSource")
    public Object queryDataSourceList(@EncryptedArgument Empty empty){
        return dataSourceService.queryDataSourceList();
    }
}
