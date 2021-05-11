package com.ssy.api.logic.audit;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.lang.TwoTuple;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.parse.ExcelParser;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 源稽核库脚本执行器
 * @Author sunshaoyu
 * @Date 2020年07月29日-15:11
 */
@Component
@Slf4j
public class SdSqlAuditExecutor {

	@Autowired
	private FileLoader fileLoader;
	@Autowired
	private SdtContextConfig contextConfig;
	private static final ThreadLocal<List<String>> tableLocal = new ThreadLocal<>();

	/**
	 * @Description 从本地项目将全量脚本整合到稽核工具的目录中
	 * @Author sunshaoyu
	 * @Date 2020/8/3-13:22
	 */
	@Deprecated
	public void auditExecutor() {
		try {
			// 读取待校验的表
			List<String> tableList = fileLoader.loadLineAsList(
					new File(contextConfig.getSqlToolsDir() + "/etc/ln_table.txt"), SdtConst.DEFAULT_CHARSET.name());
			tableList.addAll(fileLoader.loadLineAsList(new File(contextConfig.getSqlToolsDir() + "/etc/cl_table.txt"),
					SdtConst.DEFAULT_CHARSET.name()));
			tableLocal.set(tableList);

			// 提取脚本
			TwoTuple<String, String> commonSql = extractCommonSql();
			List<TwoTuple<String, String>> busiModuleSql = extractBusiModuleSql();
			String[] sqlScriptPath = new String[] { contextConfig.getSqlToolsDir() + "/sql/ln/",
					contextConfig.getSqlToolsDir() + "/sql/cl/" };

			for (int i = 0; i < busiModuleSql.size(); i++) {
				String ddl = new StringBuffer(commonSql.getFirst()).append(busiModuleSql.get(i).getFirst()).toString();
				String dml = new StringBuffer(commonSql.getSecond()).append(busiModuleSql.get(i).getSecond())
						.toString();
				fileLoader.saveFile(ddl, sqlScriptPath[i] + "ddl/ddl.sql");
				fileLoader.saveFile(dml, sqlScriptPath[i] + "dml/dml.sql");
			}
		} catch (Exception e) {
			throw new SdtException("Script audit failed", e);
		}
	}

	/**
	 * @Author sunshaoyu
	 * @Date 2020/7/29-15:31
	 * @return com.ssy.api.entity.lang.TwoTuple<java.lang.String,java.lang.String>
	 */
	private TwoTuple<String, String> extractCommonSql() throws IOException {
		String path = contextConfig.getCommonSqlMainDir();
		if (CommUtil.isNull(path)) {
			throw new SdtException("The common script directory must be set");
		}
		return new TwoTuple<>(extractDdlSql(path), extractDmlSql(path));
	}

	/**
	 * @Description 提取业务模块脚本
	 * @Author sunshaoyu
	 * @Date 2020/7/29-16:27
	 * @return java.util.List<com.ssy.api.entity.lang.TwoTuple<java.lang.String,java.lang.String>>
	 */
	private List<TwoTuple<String, String>> extractBusiModuleSql() throws IOException {
		List<TwoTuple<String, String>> list = new ArrayList<>();
		String modulePath = contextConfig.getModuleSqlMainDir();
		if (CommUtil.isNull(modulePath)) {
			throw new SdtException("The business module script directory must be set");
		} else {
			String[] pathArray = modulePath.split(SdtConst.LIST_SPLIT_TOKEN);
			for (int i = 0; i < pathArray.length; i++) {
				String path = pathArray[i];
				list.add(new TwoTuple<>(extractDdlSql(path), extractDmlSql(path)));
			}
		}
		return list;
	}

	/**
	 * @Description 提取ddl的sql脚本
	 * @Author sunshaoyu
	 * @Date 2020/7/29-15:22
	 * @param path
	 * @return java.lang.String
	 */
	protected String extractDdlSql(String path) throws IOException {
		StringBuilder ddlPath = new StringBuilder(path).append(File.separator).append("ddl");
		StringBuffer buffer = new StringBuffer();

		Map<String, File> ddlSqlMap = fileLoader.load(ddlPath.toString(), false);
		for (String key : ddlSqlMap.keySet()) {
			buffer.append(fileLoader.loadAsString(ddlSqlMap.get(key), SdtConst.DEFAULT_CHARSET.name())).append("\r\n");
		}
		return buffer.toString();
	}

	/**
	 * @Description 提取dml的sql脚本
	 * @Author sunshaoyu
	 * @Date 2020/7/29-15:27
	 * @param path
	 * @return java.lang.String
	 */
	protected String extractDmlSql(String path) throws IOException {
		StringBuilder dmlPath = new StringBuilder(path).append(File.separator).append("template");
		StringBuilder builder = new StringBuilder();

		Map<String, File> dmlSqlMap = fileLoader.load(dmlPath.toString(), false);
		for (String key : dmlSqlMap.keySet()) {
			if (ExcelParser.isExcel(key) && tableLocal.get().contains(SdtBusiUtil.getDotLeft(key))) {
				builder.append(ExcelParser.extractSqlScripts(dmlSqlMap.get(key).getPath()));
			}
		}
		return builder.toString();
	}
}
