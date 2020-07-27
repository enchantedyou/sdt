package com.ssy.api.controller;

import com.github.pagehelper.PageInfo;
import com.ssy.api.entity.annotation.EncryptedArgument;
import com.ssy.api.entity.annotation.TrxnEvent;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.lang.ResponseData;
import com.ssy.api.entity.table.local.SdbBatchExecution;
import com.ssy.api.entity.table.local.SdpBatchDate;
import com.ssy.api.entity.type.local.*;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.servicetype.BatchService;
import com.ssy.api.servicetype.MetaService;
import com.ssy.api.servicetype.UserService;
import com.ssy.api.utils.http.HttpServletUtil;
import com.ssy.api.utils.system.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月11日-13:57
 */
@Slf4j
@RestController
@RequestMapping("/local")
public class LocalController {

    @Autowired
    private UserService userService;
    @Autowired
    private MetaService metaService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private FileLoader fileLoader;

    /**
     * @param loginIn
     * @return com.ssy.api.entity.type.local.SdLoginOut
     * @Description 用户登录
     * @Author sunshaoyu
     * @Date 2020/7/11-17:03
     */
    @TrxnEvent("login")
    @PostMapping("/login")
    public SdLoginOut login(@EncryptedArgument SdLoginIn loginIn) {
        return userService.login(loginIn);
    }

    /**
     * @param key
     * @return com.github.pagehelper.PageInfo<com.ssy.api.entity.type.local.SdSearchDictOut>
     * @Description 搜索字典列表
     * @Author sunshaoyu
     * @Date 2020/7/16-13:48
     */
    @TrxnEvent("search dictionary list")
    @PostMapping("/searchDictList")
    public PageInfo<SdSearchDictOut> searchDictList(@EncryptedArgument String key) {
        return metaService.queryDictListFuzzy(key);
    }

    /**
     * @Description 查询批量日期
     * @Author sunshaoyu
     * @Date 2020/7/17-14:56
     * @param empty
     * @return com.ssy.api.entity.table.local.SdpBatchDate
     */
    @TrxnEvent("query batch date")
    @PostMapping("/queryBatchDate")
    public SdpBatchDate queryLatestBatch(@EncryptedArgument Empty empty){
        return batchService.queryBatchDate();
    }

    /**
     * @Description 倒序查询批量执行列表
     * @Author sunshaoyu
     * @Date 2020/7/17-15:38
     * @param empty
     * @return com.github.pagehelper.PageInfo<com.ssy.api.entity.table.local.SdbBatchExecution>
     */
    @TrxnEvent("query batch execution book list")
    @PostMapping("/queryBatchExeList")
    public PageInfo<SdbBatchExecution> queryBatchExeList(@EncryptedArgument Empty empty){
        return new PageInfo<>(batchService.queryBatchExeList());
    }

    /**z
     * @Description 启动批量
     * @Author sunshaoyu
     * @Date 2020/7/17-17:06
     * @param empty
     * @return com.ssy.api.entity.lang.ResponseData
     */
    @TrxnEvent("run batch")
    @PostMapping("/runBatch")
    public ResponseData runBatch(@EncryptedArgument Empty empty){
        batchService.runBatch();
        return new ResponseData();
    }

    /**
     * @Description 检查登录状态
     * @Author sunshaoyu
     * @Date 2020/7/22-16:35
     * @param empty
     * @return com.ssy.api.entity.lang.ResponseData
     */
    @PostMapping("/checkAuth")
    public ResponseData checkLoginValid(@EncryptedArgument Empty empty){
        return new ResponseData();
    }

    /**
     * @Description 构建PTE json
     * @Author sunshaoyu
     * @Date 2020/7/23-16:54
     * @param buildPTE
     * @return java.lang.String
     */
    @TrxnEvent("build PTE json")
    @PostMapping("/buildPTE")
    public String buildPTEJson(@EncryptedArgument SdBuildPTE buildPTE, HttpServletRequest request) throws Exception {
        String json = SdPTEJsonParser.buildPTEJson(buildPTE);
        fileLoader.saveFile(json.getBytes(SdtConst.DEFAULT_ENCODING), HttpServletUtil.getApplicationTargetPath(request, SdtConst.PTE_SAVE_PATH + SdPTEJsonParser.getPTEJsonFileName(buildPTE)));
        return json;
    }

    /**
     * @Description 下载PTE json
     * @Author sunshaoyu
     * @Date 2020/7/24-11:08
     * @param buildPTE
     * @param request
     * @return org.springframework.http.ResponseEntity<org.springframework.core.io.FileSystemResource>
     */
    @RequestMapping("/downloadPTEJson")
    public void downloadPTEJson(SdBuildPTE buildPTE, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BizUtil.fieldNotNull(buildPTE.getFlowtranId(), SdtDict.A.flowtran_id.getId(), SdtDict.A.flowtran_id.getLongName());
        BizUtil.fieldNotNull(buildPTE.getPteModule(), SdtDict.A.pte_module.getId(), SdtDict.A.pte_module.getLongName());
        File downloadFile = new File(HttpServletUtil.getApplicationTargetPath(request, SdtConst.PTE_SAVE_PATH + SdPTEJsonParser.getPTEJsonFileName(buildPTE)));

        if(!downloadFile.exists()) {
            throw SdtServError.E0015(buildPTE.getFlowtranId(), buildPTE.getPteModule());
        }
        HttpServletUtil.fileDownload(downloadFile, response);
    }
}
