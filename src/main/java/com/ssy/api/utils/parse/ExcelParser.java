package com.ssy.api.utils.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ssy.api.exception.SdtException;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;

/**
 * @Description excel解析器
 * @Author sunshaoyu
 * @Date 2021年07月01日-19:23
 */
public class ExcelParser {

	/**
	 * @Description 检查文件是否为excel
	 * @Author sunshaoyu
	 * @Date 2021/7/1-19:23
	 * @param fileName
	 * @return boolean
	 */
	public static boolean isExcel(String fileName) {
		return CommUtil.isNotNull(fileName) && (fileName.contains("xls") || fileName.contains("xlsx"));
	}

	/**
	 * @Description 根据excel文件类型获取Workbook对象;如果是xls类型的excel文件返回HSSFWorkbook对象,如果是xlsx类型的excel文件返回XSSFWorkbook对象
	 * @Author sunshaoyu
	 * @Date 2020/7/29-15:01
	 * @param path
	 * @return org.apache.poi.ss.usermodel.Workbook
	 */
	public static Workbook getWorkbook(String path) throws IOException {
		String excelSuffix = BizUtil.getFileType(path);
		try (FileInputStream inputStream = new FileInputStream(new File(path))) {
			if (CommUtil.equals("xls", excelSuffix)) {
				return new HSSFWorkbook(inputStream);
			} else if (CommUtil.equals("xlsx", excelSuffix)) {
				return new XSSFWorkbook(inputStream);
			} else {
				throw new SdtException("Illegal excel file type [" + excelSuffix + "]");
			}
		}
	}

	/**
	 * @Description 导出excel数据
	 * @Author sunshaoyu
	 * @Date 2021/7/1-19:39
	 * @param path     excel路径
	 * @param sheetIdx 工作簿下标，从0开始
	 * @return java.util.Map<java.lang.String,java.util.List<java.lang.String>>
	 */
	public static List<List<String>> extract(String path, int sheetIdx) {
		List<List<String>> dataList = new ArrayList<>();
		try {
			Workbook workbook = getWorkbook(path);
			Sheet sheet = workbook.getSheetAt(sheetIdx);
			Integer maxRowNum = sheet.getPhysicalNumberOfRows();
			Row firstRow = sheet.getRow(0);
			Integer maxColNum = firstRow.getPhysicalNumberOfCells();

			// 遍历行
			for (int i = 0; i < maxRowNum; i++) {
				List<String> rowList = new ArrayList<>();
				Row curRow = sheet.getRow(i);
				if (null == curRow) {
					continue;
				}
				// 遍历列
				for (int j = 0; j < maxColNum; j++) {
					final Object data = getCellObjectData(curRow.getCell(j));
					if (null == data) {
						rowList.add("");
					} else {
						rowList.add(String.valueOf(data).trim());
					}
				}
				dataList.add(rowList);
			}
		} catch (Exception e) {
			throw new SdtException("extract excel data failed:", e);
		}
		return dataList;
	}

	/**
	 * @Description 获取对应类型的cell中的值
	 * @Author sunshaoyu
	 * @Date 2020/7/29-15:04
	 * @param cell
	 * @return java.lang.Object
	 */
	protected static Object getCellObjectData(Cell cell) {
		Object cellData = null;
		if (CommUtil.isNotNull(cell)) {
			CellType cellType = cell.getCellTypeEnum();
			if (CellType.BLANK == cellType || CellType._NONE == cellType) {
				cellData = "";
			} else if (CellType.BOOLEAN == cellType) {
				cellData = cell.getBooleanCellValue();
			} else if (CellType.ERROR == cellType) {
				cellData = cell.getErrorCellValue();
			} else if (CellType.FORMULA == cellType) {
				try {
					cellData = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalStateException e) {
					cellData = String.valueOf(cell.getRichStringCellValue());
				}
			} else if (CellType.NUMERIC == cellType) {
				return String.format("%.2f", cell.getNumericCellValue());
			} else if (CellType.STRING == cellType) {
				cellData = cell.getStringCellValue();
			}
		}
		return cellData;
	}
}
