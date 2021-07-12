package com.ssy.api.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;

import com.ssy.api.utils.parse.ExcelParser;
import com.ssy.api.utils.parse.XmlParser;

import lombok.Data;

/**
 * @Description 名通接口统计
 * @Author sunshaoyu
 * @Date 2021年07月10日-09:20
 */
public class MtexApiStatistics {

	private static final String ANNOTATION_TOKEN = "@RequestMapping";
	private static final String RETURN_TOKEN = "return";
	private static final Pattern PATTERN = Pattern.compile(ANNOTATION_TOKEN + "\\(\"(.*?)\"\\)");
	private static final String INTERFACE_NAME_METHOD = "setInterfaceName";
	private static final String EXCEL_TEMPLATE_PATH = "C:\\Users\\admin\\Desktop\\模板.xlsx";
	private static final String EXCEL_OUTPUT_PATH = "C:\\Users\\admin\\Desktop\\信息快报（新）.xlsx";

	@Test
	public void execute() throws Exception {
		final String path = "E:\\mastercom\\workspace\\shandongWork\\mtapp-tmos-sd\\mtapp-mtno-sdrb\\src\\main\\java";
		List<ApiInfo> apiInfoList = new ArrayList<>();
		iterateOverFiles(path, apiInfoList);
		writeExcel(apiInfoList);
	}

	private void iterateOverFiles(String path, List<ApiInfo> apiInfoList) {
		try {
			File file = new File(path);
			final File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					iterateOverFiles(f.getPath(), apiInfoList);
				} else {
					apiInfoList.addAll(analysisApi(f));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("遍历文件失败");
		}
	}

	private List<ApiInfo> analysisApi(File file) {
		List<ApiInfo> apiInfoList = new ArrayList<>();
		String suffix = file.getName().substring(file.getName().lastIndexOf('.') + 1);
		if ("java".equals(suffix)) {
			ApiInfo apiInfo = parseJava(file);
			if (apiInfo != null) {
				apiInfoList.add(apiInfo);
			}
		} else if ("xml".equals(suffix)) {
			apiInfoList.addAll(parseXml(file));
		}
		return apiInfoList;
	}

	private void writeExcel(List<ApiInfo> apiInfoList) {
		try {
			int authNum = 0, noAuthNum = 0;
			int rowIndex = 7;
			final Workbook workbook = ExcelParser.getWorkbook(EXCEL_TEMPLATE_PATH);
			final Sheet sheet = workbook.getSheetAt(0);

			for (int i = 0; i < apiInfoList.size(); i++) {
				final ApiInfo apiInfo = apiInfoList.get(i);
				if ("是".equals(apiInfo.getTokenAuthInd())) {
					authNum++;
				} else {
					noAuthNum++;
				}

				final Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(String.valueOf(i + 1));
				row.createCell(1).setCellValue(apiInfo.getModule());
				row.createCell(2).setCellValue(apiInfo.getType());
				row.createCell(3).setCellValue(apiInfo.getInterfaceName());
				row.createCell(4).setCellValue(apiInfo.getUrl());
				row.createCell(5).setCellValue(apiInfo.getTokenAuthInd());
				row.createCell(6).setCellValue(apiInfo.getRequestEncryptInd());
				row.createCell(7).setCellValue(apiInfo.getResponseEncryptInd());
				row.createCell(8).setCellValue(apiInfo.getFactorySource());
				row.createCell(9).setCellValue(apiInfo.getFactoryName());
				row.createCell(10).setCellValue(apiInfo.getH5Limit());
				row.createCell(11).setCellValue(apiInfo.getReason());
			}
			System.out.println("总接口数：" + apiInfoList.size() + "个，鉴权接口：" + authNum + "个，未鉴权接口：" + noAuthNum + "个");
			workbook.write(new FileOutputStream(new File(EXCEL_OUTPUT_PATH)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("接口信息写入excel失败");
		}
	}

	private List<ApiInfo> parseXml(File file) {
		List<ApiInfo> apiInfoList = new ArrayList<>(16);
		try {
			final Element rootElement = XmlParser.getXmlRootElement(file);
			final List<Element> actionList = XmlParser.searchTargetAllXmlElement(rootElement, "action");
			for (Element action : actionList) {
				ApiInfo apiInfo = new ApiInfo();
				apiInfo.setUrl(action.attributeValue("name"));
				apiInfo.setModule(apiInfo.getUrl().split("/")[1]);
				apiInfo.setInterfaceName(action.attributeValue("funcname"));
				apiInfo.setTokenAuthInd("true".equalsIgnoreCase(action.attributeValue("auth")) ? "是" : "否");

				if (apiInfo.getUrl() != null) {
					apiInfoList.add(apiInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析xml失败");
		}
		return apiInfoList;
	}

	private ApiInfo parseJava(File file) {
		ApiInfo apiInfo = new ApiInfo();
		try {
			final List<String> allLineList = Files.readAllLines(Paths.get(file.getPath()));
			for (int i = 0; i < allLineList.size(); i++) {
				final String line = allLineList.get(i);
				if (null == line || line.isEmpty()) {
					continue;
				}

				// 接口路径
				if (line.trim().startsWith(ANNOTATION_TOKEN)) {
					final Matcher matcher = PATTERN.matcher(line);
					if (matcher.matches()) {
						apiInfo.setUrl(matcher.group(1));
						apiInfo.setModule(apiInfo.getUrl().split("/")[1]);
					}
				}
				// 接口名称
				else if (line.contains(INTERFACE_NAME_METHOD)) {
					for (int j = i + 1; j < allLineList.size(); j++) {
						final String afterLine = allLineList.get(j);
						if (afterLine.trim().startsWith(RETURN_TOKEN)) {
							final String interfaceName = afterLine.split(RETURN_TOKEN)[1].split(";")[0].trim();
							apiInfo.setInterfaceName(interfaceName.substring(1, interfaceName.length() - 1));
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析java文件失败");
		}
		return apiInfo.getUrl() == null ? null : apiInfo;
	}
}

@Data
class ApiInfo {
	private String module;
	private String type = "接口";
	private String interfaceName;

	private String url;
	private String tokenAuthInd = "否";
	private String requestEncryptInd = "是";
	private String responseEncryptInd = "是";

	private String factorySource = "自身";
	private String factoryName;
	private String h5Limit = "需token授权访问";
	private String reason;
}
