package com.ssy.api.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Description PDF助手
 * @Author sunshaoyu
 * @Date 2020年06月15日-17:14
 */
public class PDFHelper {

    /** PDF文档构造器 **/
    private static PDFBuilder pdfBuilder;

    @Autowired
    public void setPdfBuilder(PDFBuilder pdfBuilder) {
        PDFHelper.pdfBuilder = pdfBuilder;
    }

    /**
     * @Description 导入自定义的ppf构造器
     * @Author sunshaoyu
     * @Date 2020/6/17-11:29
     * @param builder
     */
    public static void importCustomizedBuilder(PDFBuilder builder){
        if(CommUtil.isNotNull(builder)){
            pdfBuilder = BizUtil.clone(PDFBuilder.class, builder);
        }
    }

    /**
     * @Description 创建pdf文档
     * @Author sunshaoyu
     * @Date 2020/6/17-13:40
     * @param topTitle  一级标题
     * @param tableHeaderMap  表头(字段名集合, 描述)
     * @param dataList  数据列表
     * @param valueMappingMap  数据映射(字段名, (原值, 映射值)),例如当数据某字段的值为[Y]时, 最终值为[Y-是]
     * @param outputPath    输出路径
     * @param fileName  文件名
     */
    public static void createNewPdf(String topTitle, Map<List<String>, String> tableHeaderMap, List<? extends Object> dataList, Map<String, Map<String, String>> valueMappingMap, String outputPath, String fileName) {
        //加载PDF文档默认构造器
        importCustomizedBuilder(new PDFBuilder());
        Document document = new Document(pdfBuilder.getRectangleSize());
        PdfWriter pdfWriter = null;

        try{
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(new StringBuffer(outputPath).append(File.separator).append(fileName).toString()));
            BaseFont baseFont = pdfBuilder.getBaseFont();
            Font headerFont = new Font(baseFont, pdfBuilder.getFontSizeHeader(), Font.BOLD);
            Font bodyFont = new Font(baseFont, pdfBuilder.getFontSizeBody(), Font.NORMAL);

            //添加水印和页码事件
            pdfWriter.setPageEvent(pdfBuilder);
            document.open();

            //添加一级标题
            if(CommUtil.isNotNull(topTitle)) {
                pdfBuilder.addDocumentTitle(document, topTitle);
            }

            PdfPTable globalTable = new PdfPTable(tableHeaderMap.size());
            //添加表头描述
            for(List<String> headerList : tableHeaderMap.keySet()){
                PdfPCell cell = new PdfPCell(new Paragraph(tableHeaderMap.get(headerList), headerFont));
                addSingleCell(globalTable, cell, true);
            }

            //添加表体数据
            for(Object obj : dataList){
                for(List<String> headerList : tableHeaderMap.keySet()){
                    String value = getEntitySpecificValue(valueMappingMap, obj, headerList);
                    PdfPCell cell = new PdfPCell(new Paragraph(value, bodyFont));
                    addSingleCell(globalTable, cell, false);
                }
            }
            document.add(globalTable);
        }catch (Exception e){
            throw new RuntimeException("pdf document processing failed", e);
        }finally {
            document.close();
            pdfWriter.close();
        }
    }

    /**
     * @Description 获取数据体指定字段的值
     * @Author sunshaoyu
     * @Date 2020/6/17-15:13
     * @param valueMappingMap
     * @param obj
     * @param headerList
     * @return java.lang.String
     */
    private static String getEntitySpecificValue(Map<String, Map<String, String>> valueMappingMap, Object obj, List<String> headerList) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String value = new String();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            String fieldName = field.getName();

            if(headerList.contains(fieldName)){
                value = String.valueOf(obj.getClass().getMethod(CommUtil.buildGetterMethodName(fieldName)).invoke(obj));
               if(CommUtil.isNotNull(valueMappingMap)){
                   Map<String, String> mappingSubMap = valueMappingMap.get(fieldName);
                   if(CommUtil.isNotNull(mappingSubMap) && CommUtil.isNotNull(mappingSubMap.get(value))){
                       value = String.format("%s-%s", value, mappingSubMap.get(value));
                   }
               }
                break;
            }
        }
        return value;
    }

    /**
     * @Description 添加pdf表格的单个元素
     * @Author sunshaoyu
     * @Date 2020/6/17-9:58
     * @param globalTable
     * @param cell
     * @param isHeader
     */
    private static void addSingleCell(PdfPTable globalTable, PdfPCell cell, boolean isHeader) {
        //设置为水平居中且垂直居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        //设置表头的背景颜色
        if(isHeader){
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        globalTable.addCell(cell);
    }
}