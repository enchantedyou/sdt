package com.ssy.api;

import com.ssy.api.dao.mapper.ap.ApsAccountingEventMapper;
import com.ssy.api.dao.mapper.ct.CtpLanguagePacketMapper;
import com.ssy.api.dao.mapper.ct.SmpSysDictLanguageMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceControlMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceOutMapper;
import com.ssy.api.dao.mapper.ln.LnaContractMapper;
import com.ssy.api.dao.mapper.ln.LnaLoanMapper;
import com.ssy.api.dao.mapper.ln.LnaRepaymentScheduleMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.ct.CtpLanguagePacket;
import com.ssy.api.entity.table.ct.SmpSysDictLanguage;
import com.ssy.api.entity.table.edsp.TspServiceIn;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.logic.audit.SdSqlAuditExecutor;
import com.ssy.api.logic.higention.SdGitlabReader;
import com.ssy.api.logic.local.SdFlowtranParser;
import com.ssy.api.logic.local.SdMessageConvert;
import com.ssy.api.logic.request.SdIcoreRequest;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfService;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.servicetype.LoanService;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.servicetype.UserService;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
	@Autowired
	private LnaContractMapper lnaContractMapper;
	@Autowired
	private LnaLoanMapper lnaLoanMapper;
	@Autowired
	private SmpSysDictLanguageMapper smpSysDictLanguageMapper;
	@Autowired
	private CtpLanguagePacketMapper ctpLanguagePacketMapper;

	static {
		System.setProperty("jasypt.encryptor.password", SdtConst.CONFIG_ENCKEY);
	}

	@Test
	void contextLoads() throws Throwable {
		System.out.println(RedisHelper.hasKey("123"));
	}

	private String replace(String orgStr) {
		orgStr = orgStr.replaceAll("Interest", "Profit");
		orgStr = orgStr.replaceAll("interest", "profit");
		orgStr = orgStr.replaceAll("Benifit", "Profit");
		orgStr = orgStr.replaceAll("benifit", "profit");
		orgStr = orgStr.replaceAll("Benefit", "Profit");
		orgStr = orgStr.replaceAll("benefit", "profit");
		orgStr = orgStr.replaceAll("Loan", "Financing");
		orgStr = orgStr.replaceAll("loan", "financing");
		return orgStr;
	}

	void IslamicPpocSmp_sys_dict_language() throws Exception {
		StringBuilder baseLineBuilder = new StringBuilder(1 << 10);
		StringBuilder addBuilder = new StringBuilder(1 << 10);
		String deleteFormat = "delete from smp_sys_dict_language where dict_type = '%s' and dict_id = '%s' and language_type = '%s';";
		String insertFormat = "INSERT INTO smp_sys_dict_language (DICT_TYPE, DICT_ID, LANGUAGE_TYPE, DICT_NAME) VALUES ('%s', '%s', '%s', '%s');";
		DBContextHolder.switchToDataSource("cgc_sump");
		List<SmpSysDictLanguage> dictList = smpSysDictLanguageMapper.selectAll();

		dictList.forEach(e -> {
			baseLineBuilder.append(String.format(deleteFormat, e.getDictType(), e.getDictId(), e.getLanguageType()))
					.append("\r\n");
			addBuilder.append(String.format(deleteFormat, e.getDictType(), e.getDictId(), e.getLanguageType()))
					.append("\r\n");
		});

		dictList.forEach(e -> {
			baseLineBuilder.append(
					String.format(insertFormat, e.getDictType(), e.getDictId(), e.getLanguageType(), e.getDictName()))
					.append("\r\n");
			addBuilder.append(String.format(insertFormat, e.getDictType(), e.getDictId(), e.getLanguageType(),
					replace(e.getDictName()))).append("\r\n");
		});
		baseLineBuilder.append("commit;");
		addBuilder.append("commit;");
		Files.write(Paths.get("C:\\Users\\DELL\\Desktop\\smp_sys_dict_language_add.sql"),
				addBuilder.toString().getBytes("utf-8"));
		Files.write(Paths.get("C:\\Users\\DELL\\Desktop\\smp_sys_dict_language_baseline.sql"),
				baseLineBuilder.toString().getBytes("utf-8"));
	}

	void IslamicPpocCtp_language_packet() throws Exception {
		StringBuilder baseLineBuilder = new StringBuilder(1 << 10);
		StringBuilder addBuilder = new StringBuilder(1 << 10);
		String deleteFormat = "delete from ctp_language_packet where language_resource_type = '%s' and language_resource_key = '%s' and ui_language = '%s';";
		String insertFormat = "INSERT INTO ctp_language_packet (language_resource_type, language_resource_key, ui_language, language_resource_value, data_create_time, data_update_time, data_create_user, data_update_user, data_version) VALUES ('%s', '%s', '%s', '%s', 'S####', '20170301 09:30:11 233', NULL, NULL, '0');";
		DBContextHolder.switchToDataSource("cgc_ct");
		List<CtpLanguagePacket> ctpLanguagePacketList = ctpLanguagePacketMapper.selectAll();

		ctpLanguagePacketList.forEach(e -> {
			addBuilder.append(String.format(deleteFormat, e.getLanguageResourceType(), e.getLanguageResourceKey(),
					e.getUiLanguage())).append("\r\n");
			baseLineBuilder.append(String.format(deleteFormat, e.getLanguageResourceType(), e.getLanguageResourceKey(),
					e.getUiLanguage())).append("\r\n");
		});

		ctpLanguagePacketList.forEach(e -> {
			addBuilder.append(String.format(insertFormat, e.getLanguageResourceType(), e.getLanguageResourceKey(),
					e.getUiLanguage(), replace(e.getLanguageResourceValue()))).append("\r\n");
			baseLineBuilder.append(String.format(insertFormat, e.getLanguageResourceType(), e.getLanguageResourceKey(),
					e.getUiLanguage(), e.getLanguageResourceValue())).append("\r\n");
		});
		baseLineBuilder.append("commit;");
		addBuilder.append("commit;");
		Files.write(Paths.get("C:\\Users\\DELL\\Desktop\\ctp_language_packet_baseline.sql"),
				baseLineBuilder.toString().getBytes("utf-8"));
		Files.write(Paths.get("C:\\Users\\DELL\\Desktop\\ctp_language_packet_add.sql"),
				addBuilder.toString().getBytes("utf-8"));
	}

	public void reversalService() {
		List<IntfService> serviceList = new ArrayList<>();

		// 服务接入表
		tspServiceInMapper.selectAll("rpc").forEach(tspServiceIn -> {
			if ("D".equals(tspServiceIn.getTransactionMode()) && "T".equals(tspServiceIn.getServiceCategory())) {
				Flowtran flowtran = SdFlowtranParser.load(tspServiceIn.getInnerServiceCode());
				serviceList.addAll(flowtran.getServiceList());
				log.info("交易{}的服务数:{},服务列表:{}", tspServiceIn.getInnerServiceCode(), flowtran.getServiceList().size(),
						flowtran.getServiceList());
			}
		});

		serviceList.forEach(service -> {
			TspServiceIn tspServiceIn = tspServiceInMapper
					.selectOne(CommUtil.nvl(service.getId(), service.getServiceName()));
			if (CommUtil.isNotNull(tspServiceIn)) {
				tspServiceIn.setTransactionMode("D");
				tspServiceInMapper.updateByPrimaryKey(tspServiceIn);
			}
		});

		// 服务控制表
		tspServiceControlMapper.selectAll().forEach(tspServiceControl -> {
			String cancelService = tspServiceControl.getCancelService();
			if (CommUtil.isNotNull(cancelService) && cancelService.toLowerCase().contains("cancel")) {
				tspServiceControl.setServiceTransactionMode("Required");
				tspServiceControl.setServiceType("try");
				tspServiceControlMapper.updateByPrimaryKey(tspServiceControl);
			}
		});
	}

	public void dockerRest() {
		List<SdpModuleMapping> moduleMappingList = moduleMapService.queryAllModuleList();
		Map<String, String> rollbackMap = new HashMap<>();

		// 服务接入表
		tspServiceInMapper.selectAll("rpc").forEach(tspServiceIn -> {
			tspServiceIn.setProtocolId("rest");
			if (CommUtil.equals(tspServiceIn.getServiceCategory(), "T")) {
				tspServiceIn.setOutServiceCode("/" + tspServiceIn.getInnerServiceCode());
			} else if (CommUtil.equals(tspServiceIn.getServiceCategory(), "S")) {
				String[] outServiceCode = tspServiceIn.getOutServiceCode().split("\\.");
				String newOutServiceCode = "/" + outServiceCode[0].substring(0, 1).toLowerCase()
						+ outServiceCode[0].substring(1) + "/" + outServiceCode[1];

				if (outServiceCode[0].toLowerCase().contains("txc")
						|| outServiceCode[0].toLowerCase().contains("msreversal")) {
					newOutServiceCode = "/" + tspServiceIn.getInnerServiceImplCode().substring(0, 1).toLowerCase()
							+ tspServiceIn.getInnerServiceImplCode().substring(1) + newOutServiceCode;
					rollbackMap.put(tspServiceIn.getInnerServiceCode(), newOutServiceCode);
				}
				tspServiceIn.setOutServiceCode(newOutServiceCode);
			}
			if (tspServiceIn.getOutServiceCode().length() > 50) {
				System.out.println(tspServiceIn.getOutServiceCode());
			}
			tspServiceInMapper.insert(tspServiceIn);
		});
		// 移除rpc协议的服务接入信息
		tspServiceInMapper.selectAll("rpc").forEach(tspServiceIn -> {
			tspServiceInMapper.deleteByPrimaryKey(tspServiceIn.getSystemCode(), tspServiceIn.getSubSystemCode(),
					tspServiceIn.getOutServiceCode());
		});

		// 服务接出表
		tspServiceOutMapper.selectAll("remote_rpc").forEach(tspServiceOut -> {
			tspServiceOut.setProtocolId("rest");
			tspServiceOut.setOutServiceApp(
					CommUtil.nvl(getServiceOutApp(moduleMappingList, tspServiceOut.getOutServiceApp()),
							tspServiceOut.getOutServiceApp()));
			String[] outServiceCode = tspServiceOut.getOutServiceCode().split("\\.");
			if (outServiceCode.length >= 2) {
				String newOutServiceCode = "/" + outServiceCode[0].substring(0, 1).toLowerCase()
						+ outServiceCode[0].substring(1) + "/" + outServiceCode[1];
				String rollbackService = rollbackMap.get(tspServiceOut.getInnerServiceCode());
				if (CommUtil.isNotNull(rollbackService)) {
					newOutServiceCode = rollbackService;
				}
				tspServiceOut.setOutServiceCode(newOutServiceCode);
				tspServiceOut.setOutServiceGroup("POST");
				tspServiceOutMapper.updateByPrimaryKey(tspServiceOut);
			}
		});
	}

	private String getServiceOutApp(List<SdpModuleMapping> moduleMappingList, String subSystemCode) {
		for (SdpModuleMapping moduleMapping : moduleMappingList) {
			String moduleId = moduleMapping.getModuleId();
			if (CommUtil.equals(moduleMapping.getSubSystemCode(), subSystemCode)) {
				if (CommUtil.equals("cf", moduleId)) {
					moduleId = "us";
				}
				return moduleId.toLowerCase() + "-onl";
			}
		}
		return null;
	}

	void doRequest312020(int concurrentNum) throws IOException {
		String url = "http://10.22.63.72:9009/gateway";
		String body = fileLoader.loadAsString(new File("C:\\Users\\DELL\\Desktop\\312020.json"),
				SdtConst.DEFAULT_CHARSET.name());
		SdIcoreRequest.doRequest(url, body, concurrentNum);
	}
}
