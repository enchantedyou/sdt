package com.ssy.api.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ssy.api.entity.annotation.DisableXss;
import com.ssy.api.entity.annotation.EncryptedArgument;
import com.ssy.api.entity.annotation.TrxnEvent;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.lang.ResponseData;
import com.ssy.api.entity.table.local.SdbBatchExecution;
import com.ssy.api.entity.table.local.SdpBatchDate;
import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.entity.type.local.*;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.logic.builder.SdFieldSetBuilder;
import com.ssy.api.logic.builder.SdScriptBuilder;
import com.ssy.api.logic.builder.SdTrxnBuilder;
import com.ssy.api.logic.higention.SdGitlabReader;
import com.ssy.api.logic.higention.SdNexus;
import com.ssy.api.logic.local.SdMessageConvert;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.logic.local.SdRdpEventBuilder;
import com.ssy.api.servicetype.*;
import com.ssy.api.utils.http.HttpServletUtil;
import com.ssy.api.utils.parse.SunlineExcelParser;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.JDBCHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 本地相关服务的控制层
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
	private DataSourceService dataSourceService;
	@Autowired
	private LoanService loanService;

	@Autowired
	private BatchService batchService;
	@Autowired
	private SdGitlabReader gitlabReader;
	@Autowired
	private FileLoader fileLoader;
	@Autowired
	private JDBCHelper jdbcHelper;
	@Autowired
	private SdMessageConvert messageConvert;

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
	 * @Description 注销登录
	 * @Author sunshaoyu
	 * @Date 2020/9/19-23:36
	 * @param empty
	 */
	@TrxnEvent("logout")
	@PostMapping("/logout")
	public void logout(@EncryptedArgument Empty empty) {
		userService.logout();
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
	public SdpBatchDate queryLatestBatch(@EncryptedArgument Empty empty) {
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
	public PageInfo<SdbBatchExecution> queryBatchExeList(@EncryptedArgument Empty empty) {
		return new PageInfo<>(batchService.queryBatchExeList());
	}

	/**
	 * z
	 * 
	 * @Description 启动批量
	 * @Author sunshaoyu
	 * @Date 2020/7/17-17:06
	 * @param empty
	 * @return com.ssy.api.entity.lang.ResponseData
	 */
	@TrxnEvent("run batch")
	@PostMapping("/runBatch")
	public ResponseData runBatch(@EncryptedArgument Empty empty) {
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
	public ResponseData checkLoginValid(@EncryptedArgument Empty empty) {
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
	public String buildPTEJson(@EncryptedArgument SdBuildPTE buildPTE, HttpServletRequest request) throws IOException {
		String json = SdPTEJsonParser.buildPTEJson(buildPTE);
		fileLoader.saveFile(json.getBytes(SdtConst.DEFAULT_CHARSET), HttpServletUtil.getApplicationTargetPath(request,
				SdtConst.PTE_SAVE_PATH + SdPTEJsonParser.getPTEJsonFileName(buildPTE)));
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
	public void downloadPTEJson(SdBuildPTE buildPTE, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		BizUtil.fieldNotNull(buildPTE.getFlowtranId(), SdtDict.A.flowtran_id.getId(),
				SdtDict.A.flowtran_id.getLongName());
		BizUtil.fieldNotNull(buildPTE.getPteModule(), SdtDict.A.pte_module.getId(), SdtDict.A.pte_module.getLongName());
		File downloadFile = new File(HttpServletUtil.getApplicationTargetPath(request,
				SdtConst.PTE_SAVE_PATH + SdPTEJsonParser.getPTEJsonFileName(buildPTE)));

		if (!downloadFile.exists()) {
			throw SdtServError.E0015(buildPTE.getFlowtranId(), buildPTE.getPteModule());
		}
		HttpServletUtil.fileDownload(downloadFile, response);
	}

	/**
	 * @Description 从gitlab读取合并文件差异
	 * @Author sunshaoyu
	 * @Date 2020/7/31-14:20
	 * @param moduleId
	 * @param jiraId
	 * @return java.lang.String
	 */
	@TrxnEvent("read gitlab merge diffs")
	@PostMapping("/mergeDiffs")
	public String readMergeDiffs(@EncryptedArgument String moduleId, @EncryptedArgument String jiraId) {
		return gitlabReader.getMergeDiffs(moduleId, jiraId);
	}

	/**
	 * @Description 从nexus读取各模块服务总线层的最新版本
	 * @Author sunshaoyu
	 * @Date 2020/8/5-14:52
	 * @param repositoryType
	 * @return java.lang.String
	 */
	@TrxnEvent("read nexus lastest iobus version")
	@PostMapping("/iobusVer")
	public String readIobusVer(@EncryptedArgument String repositoryType) {
		return SdNexus.getLastestIobusVersion(repositoryType);
	}

	/**
	 * @Description 接口文档生成并下载
	 * @Author sunshaoyu
	 * @Date 2020/8/6-16:36
	 * @param flowtranId
	 * @param response
	 */
	@RequestMapping("/downloadIntf")
	public void downloadInterfaceDoc(String flowtranId, HttpServletResponse response) throws IOException {
		BizUtil.fieldNotNull(flowtranId, SdtDict.A.flowtran_id.getId(), SdtDict.A.flowtran_id.getLongName());
		HttpServletUtil.downloadHeaderSet(flowtranId + SdtConst.INTF_EXCEL_SUFFIX, response);
		SunlineExcelParser.genInterfaceDoc(flowtranId, response.getOutputStream());
	}

	/**
	 * @Description 数据源列表查询
	 * @Author sunshaoyu
	 * @Date 2020/8/6-16:59
	 * @param empty
	 * @return java.util.List<com.ssy.api.entity.table.local.SdpDatasource>
	 */
	@TrxnEvent("query data source list")
	@PostMapping("/dataSourceList")
	public PageInfo<SdpDatasource> queryDataSourceList(@EncryptedArgument Empty empty) {
		return new PageInfo<>(dataSourceService.queryDataSourceList());
	}

	/**
	 * @Description 数据源维护
	 * @Author sunshaoyu
	 * @Date 2020/8/7-14:14
	 * @param datasourceEdit
	 */
	@TrxnEvent("edit data source")
	@PostMapping("/editDataSource")
	public void editDataSource(@EncryptedArgument SdDatasourceEdit datasourceEdit) {
		dataSourceService.editDataSource(datasourceEdit);
	}

	/**
	 * @Description 构建交易模型
	 * @Author sunshaoyu
	 * @Date 2020/8/11-14:32
	 * @param metaGen
	 */
	@TrxnEvent("build transaction model")
	@PostMapping("/buildTrxn")
	public void buildTrxnModel(@EncryptedArgument SdMetaGen metaGen) {
		SdTrxnBuilder.build(metaGen);
	}

	/**
	 * @Description 构建交易脚本
	 * @Author sunshaoyu
	 * @Date 2020/8/12-9:43
	 * @param flowtranId
	 * @return java.lang.String
	 */
	@TrxnEvent("build transaction script")
	@PostMapping("/trxnScript")
	public String buildTrxnScript(@EncryptedArgument String flowtranId) {
		return SdScriptBuilder.buildTransaction(flowtranId);
	}

	/**
	 * @Description 数据库解锁
	 * @Author sunshaoyu
	 * @Date 2020/8/28-15:38
	 * @param datasourceId
	 * @return int
	 */
	@TrxnEvent("database unlock")
	@PostMapping("/dbUnlock")
	public int dbUnlock(@EncryptedArgument String datasourceId) {
		return jdbcHelper.unlock(datasourceId);
	}

	/**
	 * @Description 构建字段赋值语句
	 * @Author sunshaoyu
	 * @Date 2020/9/7-10:55
	 * @param fieldSetIn
	 * @return java.lang.String
	 */
	@TrxnEvent("build field set statement")
	@PostMapping("/buildFieldSet")
	public String buildFieldSetStatement(@EncryptedArgument SdFieldSetIn fieldSetIn) {
		SdFieldSetBuilder.checkMain(fieldSetIn);
		return SdFieldSetBuilder.doMain(fieldSetIn);
	}

	/**
	 * @Description 表分片哈希值查询
	 * @Author sunshaoyu
	 * @Date 2020/10/10-9:53
	 * @param shardingHashIn
	 * @return java.lang.Long
	 */
	@TrxnEvent("query Sharding hash value")
	@PostMapping("/queryShardingHashValue")
	public Long queryShardingHashValue(@EncryptedArgument SdShardingHashIn shardingHashIn) {
		return loanService.getGroupHashValue(shardingHashIn.getUpperLimit(), shardingHashIn.getSequence()) - 1L;
	}

	/**
	 * @Description 维护用户信息
	 * @Author sunshaoyu
	 * @Date 2020/10/23-11:09
	 * @param mntUser
	 */
	@TrxnEvent("modify user information")
	@PostMapping("/modifyUser")
	public void modifyUserInfo(@EncryptedArgument SdMntUser mntUser) {
		userService.modifyUserInfo(mntUser);
	}

	/**
	 * @Description 请求报文转换为单元测试代码
	 * @Author sunshaoyu
	 * @Date 2020/10/23-16:48
	 * @param requestBody
	 * @return java.lang.String
	 */
	@DisableXss
	@TrxnEvent("build unit test java code")
	@PostMapping("/buildTestCode")
	public String buildTestCode(@EncryptedArgument String requestBody) {
		return messageConvert.toUnitTestCode(requestBody);
	}

	/**
	 * @Description rdp事件构建
	 * @Author sunshaoyu
	 * @Date 2020/11/19-13:09
	 * @param jsonName
	 * @param fieldName
	 * @return java.lang.String
	 */
	@TrxnEvent("build rdp event")
	@PostMapping("/buildRdpEvent")
	public String buildRdpEvent(@EncryptedArgument String jsonName, @EncryptedArgument String fieldName) {
		return SdRdpEventBuilder.build(jsonName, fieldName);
	}
}
