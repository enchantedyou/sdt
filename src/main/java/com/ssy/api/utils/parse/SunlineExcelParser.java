package com.ssy.api.utils.parse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssy.api.dao.mapper.ct.SmpSysDictMapper;
import com.ssy.api.dao.mapper.edsp.TspServiceInMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_IO;
import com.ssy.api.entity.table.ct.SmpSysDict;
import com.ssy.api.entity.table.edsp.TspServiceIn;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.logic.local.SdFlowtranParser;
import com.ssy.api.logic.local.SdJavaParser;
import com.ssy.api.logic.local.SdPTEJsonParser;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfFields;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description excel解析器
 * @Author sunshaoyu
 * @Date 2020年07月29日-14:48
 */
@Component
@Slf4j
public class SunlineExcelParser {

	private static SdtContextConfig contextConfig;
	private static SmpSysDictMapper sysDictMapper;
	private static TspServiceInMapper tspServiceInMapper;
	private static ModuleMapService moduleMapService;

	private static final ThreadLocal<Integer> curRowNumberLocal = new ThreadLocal<>();
	private static final ThreadLocal<List<String>> mandatoryLocal = new ThreadLocal<>();

	@Autowired
	public void setContextConfig(SdtContextConfig contextConfig) {
		SunlineExcelParser.contextConfig = contextConfig;
	}

	@Autowired
	public void setSysDictMapper(SmpSysDictMapper sysDictMapper) {
		SunlineExcelParser.sysDictMapper = sysDictMapper;
	}

	@Autowired
	public void setTspServiceInMapper(TspServiceInMapper tspServiceInMapper) {
		SunlineExcelParser.tspServiceInMapper = tspServiceInMapper;
	}

	@Autowired
	public void setModuleMapService(ModuleMapService moduleMapService) {
		SunlineExcelParser.moduleMapService = moduleMapService;
	}

	/**
	 * @Description 从excel提取sql脚本
	 * @Author sunshaoyu
	 * @Date 2020/7/29-15:07
	 * @param path
	 * @return java.lang.String
	 */
	public static String extractSqlScripts(String path) throws IOException {
		Workbook workbook = ExcelParser.getWorkbook(path);
		Sheet sheet = workbook.getSheetAt(0);
		Integer maxRowNum = sheet.getPhysicalNumberOfRows();
		Row firstRow = sheet.getRow(0);
		Integer maxColNum = firstRow.getPhysicalNumberOfCells();

		// 列数据下标
		int colDataIndex = maxColNum + 2;
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i <= maxColNum; i++) {
			Object cellData = ExcelParser.getCellObjectData(firstRow.getCell(i));
			if (CommUtil.isNull(cellData)) {
				colDataIndex = i + 1;
				break;
			}
		}

		// 遍历行
		for (int i = 0; i < maxRowNum; i++) {
			Row curRow = sheet.getRow(i);
			if (curRow != null) {
				Object rowCellData = ExcelParser.getCellObjectData(curRow.getCell(colDataIndex));
				if (CommUtil.isNotNull(rowCellData)) {
					builder.append(rowCellData).append("\r\n");
				}
			}
		}

		if (CommUtil.isNull(builder)) {
			throw new SdtException("Failed to parse excel file [" + path + "]");
		}
		builder.append("commit;\r\n");
		return builder.toString();
	}

	/**
	 * @Description 接口文档生成
	 * @Author sunshaoyu
	 * @Date 2020/8/5-17:20
	 * @param flowtranId
	 * @param outputStream
	 */
	public static void genInterfaceDoc(String flowtranId, OutputStream outputStream) {
		try {
			Flowtran flowtran = SdFlowtranParser.load(flowtranId);
			Workbook workbook = ExcelParser.getWorkbook(
					SunlineExcelParser.class.getResource("/templates/excel/intf_doc_template.xlsx").getPath());
			Sheet templateSheet = workbook.cloneSheet(3);
			templateSheet.getRow(0).getCell(1).setCellValue(moduleMapService.getServiceCode(flowtranId));

			// 指定参数类别
			switch (flowtran.getKind()) {
			case M:
				templateSheet.getRow(0).getCell(7).setCellValue("普通维护交易");
				break;
			case Q:
				templateSheet.getRow(0).getCell(7).setCellValue("查询交易");
				break;
			case F:
				templateSheet.getRow(0).getCell(7).setCellValue("金融交易");
				break;
			case P:
				templateSheet.getRow(0).getCell(7).setCellValue("参数维护交易");
				break;
			}
			templateSheet.getRow(1).getCell(1).setCellValue(flowtran.getLongName());
			// 强制换行
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setWrapText(true);

			curRowNumberLocal.set(6);
			mandatoryLocal.set(flowtran.getInput().getFieldList().size() > 50 ? new ArrayList<>()
					: SdJavaParser.searchMandatoryFields(flowtranId));
			DBContextHolder.switchToDataSource(contextConfig.getSumpDataSource());
			handleIntfDocIOField(flowtran, templateSheet, cellStyle, E_IO.INPUT);

			// 设置输出域标题
			copyRows(5, 6, curRowNumberLocal.get(), templateSheet);
			templateSheet.getRow(curRowNumberLocal.get()).getCell(0).setCellValue("输                   出");
			templateSheet.getRow(curRowNumberLocal.get() + 1).getCell(6).setCellValue("");
			templateSheet.getRow(curRowNumberLocal.get() + 1).getCell(7).setCellValue("输出说明");
			curRowNumberLocal.set(curRowNumberLocal.get() + 2);

			// 输出字段赋值
			handleIntfDocIOField(flowtran, templateSheet, cellStyle, E_IO.OUTPUT);

			// 移除后重命名
			workbook.removeSheetAt(3);
			workbook.setSheetName(3, workbook.getSheetName(3).substring(0, 4));
			workbook.write(outputStream);
		} catch (Exception e) {
			throw new SdtException("Failed to generate interface document with flowtran id [" + flowtranId + "]", e);
		}
	}

	/**
	 * @Description 创建接口文档的最新一行
	 * @Author sunshaoyu
	 * @Date 2020/8/5-17:04
	 * @param sheet
	 * @return org.apache.poi.ss.usermodel.Row
	 */
	private static Row createIntfCurrentRow(Sheet sheet) {
		Row row = sheet.createRow(curRowNumberLocal.get());
		curRowNumberLocal.set(curRowNumberLocal.get() + 1);
		return row;
	}

	/**
	 * @Description 处理接口文档输入输出字段集
	 * @Author sunshaoyu
	 * @Date 2020/8/5-17:11
	 * @param flowtran
	 * @param sheet
	 * @param cellStyle
	 * @param io
	 */
	private static void handleIntfDocIOField(Flowtran flowtran, Sheet sheet, CellStyle cellStyle, E_IO io) {
		List<Element> fieldList = io == E_IO.INPUT ? flowtran.getInput().getFieldList()
				: flowtran.getOutput().getFieldList();
		List<IntfFields> fieldsList = io == E_IO.INPUT ? flowtran.getInput().getFieldsList()
				: flowtran.getOutput().getFieldsList();
		int index = 0;

		// 处理散字段
		for (Element e : fieldList) {
			Row curRow = createIntfCurrentRow(sheet);
			handleRowBaseInfo(String.valueOf(++index), e, curRow, cellStyle);

			// 设置必输选项
			if (io == E_IO.INPUT) {
				curRow.createCell(6).setCellValue(mandatoryLocal.get().contains(e.getId()) ? "M" : "O");
			}
		}

		// 处理列表
		for (IntfFields fields : fieldsList) {
			int subIndex = 0;
			index++;
			Row curRow = createIntfCurrentRow(sheet);
			curRow.createCell(1).setCellValue(fields.getId());

			for (Element e : fields.getSubFieldList()) {
				curRow = createIntfCurrentRow(sheet);
				handleRowBaseInfo(String.format("%d.%d", index, ++subIndex), e, curRow, cellStyle);
			}
		}
	}

	/**
	 * @Description 获取字段枚举信息
	 * @Author sunshaoyu
	 * @Date 2020/10/9-16:04
	 * @param e
	 * @return java.lang.String
	 */
	private static String getIntfEnumInfo(Element e) {
		// 枚举值
		Map<String, DefaultEnumerationType> enumMap = e.getType().getEnumerationMap();
		// 按值排序
		enumMap = CommUtil.sortMap(enumMap, new Comparator<Map.Entry<String, DefaultEnumerationType>>() {
			@Override
			public int compare(Map.Entry<String, DefaultEnumerationType> o1,
					Map.Entry<String, DefaultEnumerationType> o2) {
				return CommUtil.compare(o1.getValue().getValue(), o2.getValue().getValue());
			}
		});
		if (CommUtil.isNotNull(enumMap)) {
			StringBuffer buffer = new StringBuffer();
			enumMap.forEach((k, v) -> {
				SmpSysDict smpSysDict = sysDictMapper.selectByPrimaryKey(e.getType().getId(), v.getValue());
				buffer.append(v.getValue()).append(":")
						.append(CommUtil.isNull(smpSysDict) ? v.getLongName() : smpSysDict.getDictName())
						.append(";\r\n");
			});
			return buffer.toString().substring(0, buffer.toString().lastIndexOf("\r\n"));
		}
		return new String();
	}

	/**
	 * @Description 处理行基础信息
	 * @Author sunshaoyu
	 * @Date 2020/8/5-16:57
	 * @param index
	 * @param e
	 * @param curRow
	 * @param cellStyle
	 * @return int
	 */
	private static void handleRowBaseInfo(String index, Element e, Row curRow, CellStyle cellStyle) {
		curRow.createCell(0).setCellValue(index);
		curRow.createCell(1).setCellValue(e.getId());
		curRow.createCell(2).setCellValue(e.getDesc());

		curRow.createCell(3).setCellValue(e.getType().getBase().getValue());
		curRow.createCell(4).setCellValue(SdPTEJsonParser.determineControlMaxLength(e));
		curRow.createCell(5).setCellValue(e.getType().getFractionDigits());

		// 枚举值
		Cell cell = curRow.createCell(7);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(getIntfEnumInfo(e));
	}

	/**
	 * @Description 复制行
	 * @Author sunshaoyu
	 * @Date 2020/8/5-17:15
	 * @param startRow
	 * @param endRow
	 * @param pPosition
	 * @param sheet
	 */
	private static void copyRows(int startRow, int endRow, int pPosition, Sheet sheet) {
		int pStartRow = startRow - 1;
		int pEndRow = endRow - 1;
		int targetRowFrom;
		int targetRowTo;
		int columnCount;
		CellRangeAddress region = null;
		int i;
		int j;
		if (pStartRow == -1 || pEndRow == -1) {
			return;
		}
		// 拷贝合并的单元格
		for (i = 0; i < sheet.getNumMergedRegions(); i++) {
			region = sheet.getMergedRegion(i);
			if ((region.getFirstRow() >= pStartRow)
					&& (region.getLastRow() <= pEndRow)) {
				targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
				targetRowTo = region.getLastRow() - pStartRow + pPosition;
				CellRangeAddress newRegion = region.copy();
				newRegion.setFirstRow(targetRowFrom);
				newRegion.setFirstColumn(region.getFirstColumn());
				newRegion.setLastRow(targetRowTo);
				newRegion.setLastColumn(region.getLastColumn());
				sheet.addMergedRegion(newRegion);
			}
		}
		// 设置列宽
		for (i = pStartRow; i <= pEndRow; i++) {
			Row sourceRow = sheet.getRow(i);
			columnCount = sourceRow.getLastCellNum();
			if (sourceRow != null) {
				Row newRow = sheet.createRow(pPosition - pStartRow + i);
				newRow.setHeight(sourceRow.getHeight());
				for (j = 0; j < columnCount; j++) {
					Cell templateCell = sourceRow.getCell(j);
					if (templateCell != null) {
						Cell newCell = newRow.createCell(j);
						copyCell(templateCell, newCell);
					}
				}
			}
		}
	}

	/**
	 * @Description 复制单个单元格
	 * @Author sunshaoyu
	 * @Date 2020/8/5-17:15
	 * @param srcCell
	 * @param distCell
	 */
	private static void copyCell(Cell srcCell, Cell distCell) {
		distCell.setCellStyle(srcCell.getCellStyle());
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		CellType srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);
		if (srcCellType == CellType.NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
				distCell.setCellValue(srcCell.getDateCellValue());
			} else {
				distCell.setCellValue(srcCell.getNumericCellValue());
			}
		} else if (srcCellType == CellType.STRING) {
			distCell.setCellValue(srcCell.getRichStringCellValue());
		} else if (srcCellType == CellType.BLANK) {
			// nothing
		} else if (srcCellType == CellType.BOOLEAN) {
			distCell.setCellValue(srcCell.getBooleanCellValue());
		} else if (srcCellType == CellType.ERROR) {
			distCell.setCellErrorValue(srcCell.getErrorCellValue());
		} else if (srcCellType == CellType.FORMULA) {
			distCell.setCellFormula(srcCell.getCellFormula());
		}
	}

	/**
	 * @Description 网关API生成
	 * @Author sunshaoyu
	 * @Date 2020/10/9-10:09
	 * @param protocolId
	 * @param outputStream
	 */
	public static void genGatewayApi(String protocolId, OutputStream outputStream) {
		try {
			BizUtil.fieldNotNull(protocolId, SdtDict.A.protocol_id.getId(), SdtDict.A.protocol_id.getLongName());
			Workbook workbook = ExcelParser.getWorkbook(
					SunlineExcelParser.class.getResource("/templates/excel/api_template.xlsx").getPath());
			Sheet sheet = workbook.getSheetAt(0);
			// 数据源检查
			if (CommUtil.isNull(DBContextHolder.getCurrentDataSource())) {
				throw SdtServError.E0023();
			}

			int index = 3;
			String moduleName = null;
			List<TspServiceIn> innserServiceList = tspServiceInMapper.selectAll(protocolId);
			for (TspServiceIn tspServiceIn : innserServiceList) {
				String innerServiceCode = tspServiceIn.getInnerServiceCode();
				if (CommUtil.isNull(moduleName)) {
					moduleName = moduleMapService.getModuleName(tspServiceIn.getSubSystemCode());
				}

				if (CommUtil.equals(tspServiceIn.getServiceCategory(), "T") && !innerServiceCode.contains("ap")
						&& !innerServiceCode.contains("ms")) {
					// api新增
					Row row = sheet.createRow(index++);
					row.createCell(1).setCellValue(moduleMapService.getServiceCode(tspServiceIn.getInnerServiceCode()));
					row.createCell(2).setCellValue(moduleName.toUpperCase());
					row.createCell(3).setCellValue("1.0");
					row.createCell(4).setCellValue("http");
					row.createCell(5).setCellValue("restLoad");
					row.createCell(7).setCellValue(String.format(
							"{\"rpc3load_dcn_in_body_enable\":0,\"restLoad_alloc_type\":\"NO\",\"restload_uri\":\"%s\",\"restload_application\":\"%s-onl\",\"restLoad_service_type\":\"distribute\"}",
							tspServiceIn.getOutServiceCode(), moduleName));
					row.createCell(8).setCellValue("已发布");
					row.createCell(9).setCellValue(tspServiceIn.getDescription());
				}
			}

			// 保存并写入
			workbook.write(outputStream);
		} catch (Exception e) {
			throw new SdtException("Gateway Api build failed", e);
		}
	}
}
