package com.ssy.api.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;

/**
 * @Description PDF构建器,用于实现pdf页面相关事件,以及设置相关参数
 * @Author sunshaoyu
 * @Date 2020年06月17日-11:07
 */
@Component
public class PDFBuilder implements PdfPageEvent{
    /** 字体路径 **/
    private final String DEFAULT_FONT_PATH = PDFHelper.class.getResource("/simsun.ttf").getPath();
    /** 表头字体大小 **/
    private final int DEFAULT_FONT_SIZE_HEADER = 15;
    /** 表体字体大小 **/
    private final int DEFAULT_FONT_SIZE_BODY = 12;
    /** 水印字体大小 **/
    private final int DEFAULT_FONT_SIZE_WATERMARK = 35;
    /** 水印字体大小 **/
    private final int DEFAULT_FONT_SIZE_TOPTITLE = 30;
    /** PDF单页纸张大小 **/
    private final Rectangle DEFAULT_RECTANGLE_SIZE = PageSize.A4.rotate();
    /** PDF水印标题 **/
    private final String DEFAULT_WATERMARK_TITLE = "Sunline";

    private String fontPath;
    private int fontSizeHeader;
    private int fontSizeBody;
    private int fontSizeWatermark;
    private int fontSizeTopTitle;

    private Rectangle rectangleSize;
    private String watermarkTitle;
    private String pageHeaderTitle;
    private BaseFont baseFont;
    private PdfTemplate pdfTemplate;

    public PDFBuilder(String fontPath, int fontSizeHeader, int fontSizeBody, int fontSizeWatermark, int fontSizeTopTitle, Rectangle rectangleSize, String watermarkTitle, String pageHeaderTitle) {
        this.fontPath = CommUtil.nvl(fontPath, DEFAULT_FONT_PATH);
        this.fontSizeHeader = fontSizeHeader > 0 ? fontSizeHeader : DEFAULT_FONT_SIZE_HEADER;
        this.fontSizeBody = fontSizeBody > 0 ? fontSizeBody : DEFAULT_FONT_SIZE_BODY;
        this.fontSizeWatermark = fontSizeWatermark > 0 ? fontSizeWatermark : DEFAULT_FONT_SIZE_WATERMARK;
        this.fontSizeTopTitle = fontSizeTopTitle > 0 ? fontSizeTopTitle : DEFAULT_FONT_SIZE_TOPTITLE;
        this.rectangleSize = CommUtil.nvl(rectangleSize, DEFAULT_RECTANGLE_SIZE);
        this.watermarkTitle = CommUtil.nvl(watermarkTitle, DEFAULT_WATERMARK_TITLE);
        this.pageHeaderTitle = pageHeaderTitle;
        loadBaseFont();
    }

    public PDFBuilder() {
        this.fontPath = DEFAULT_FONT_PATH;
        this.fontSizeHeader = DEFAULT_FONT_SIZE_HEADER;
        this.fontSizeBody = DEFAULT_FONT_SIZE_BODY;
        this.fontSizeWatermark = DEFAULT_FONT_SIZE_WATERMARK;
        this.fontSizeTopTitle = DEFAULT_FONT_SIZE_TOPTITLE;
        this.rectangleSize = DEFAULT_RECTANGLE_SIZE;
        this.watermarkTitle = DEFAULT_WATERMARK_TITLE;
        loadBaseFont();
    }

    /**
     * @Description 加载基础字体
     * @Author sunshaoyu
     * @Date 2020/6/17-11:20
     */
    private void loadBaseFont() {
        try{
            this.baseFont = BaseFont.createFont(this.fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        }catch(Exception e){
            throw new RuntimeException("Basic font loading failed", e);
        }
    }

    /**
     * @Description 写入页眉页脚
     * @Author sunshaoyu
     * @Date 2020/6/17-10:45
     * @param pdfWriter
     * @param document
     */
    private void addPageHeaderAndFooter(PdfWriter pdfWriter, Document document) {
        try{
            Font bodyFont = new Font(baseFont, fontSizeBody, Font.NORMAL);
            //写入页眉
            if(CommUtil.isNotNull(pageHeaderTitle)){
                ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_LEFT, new Phrase(pageHeaderTitle, bodyFont),
                        document.left(), document.top() + 20, 0);
            }
            //写入分页信息
            String pageText = String.format("第 %d 页/共", pdfWriter.getCurrentPageNumber());
            Phrase pageFooter = new Phrase(pageText, bodyFont);
            //计算前半部分分页文本的长度
            float lenOfBefore = baseFont.getWidthPoint(pageText, fontSizeBody);

            //写入页脚
            PdfContentByte contentByte = pdfWriter.getDirectContent();
            ColumnText.showTextAligned(contentByte, Element.ALIGN_CENTER, pageFooter,
                    (document.rightMargin() + document.right() + document.leftMargin() - document.left() - lenOfBefore) / 2.0f + 280f,
                    document.bottom() - 20, 0);
            contentByte.addTemplate(pdfTemplate, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0f + 280f,
                    document.bottom() - 20);
        }catch (Exception e){
            throw new RuntimeException("Failed to add header and footer", e);
        }
    }

    /**
     * @Description 添加文字水印
     * @Author sunshaoyu
     * @Date 2020/6/17-10:53
     * @param pdfWriter
     * @param watermarkText
     */
    private void addWatermarkByText(PdfWriter pdfWriter, String watermarkText){
        PdfGState pdfGState = new PdfGState();
        pdfGState.setFillOpacity(0.15f);
        pdfGState.setStrokeOpacity(0.15f);
        PdfContentByte pdfContentByte = pdfWriter.getDirectContentUnder();

        pdfContentByte.setGState(pdfGState);
        pdfContentByte.beginText();
        pdfContentByte.setFontAndSize(baseFont, fontSizeWatermark);
        pdfContentByte.setTextMatrix(30, 30);

        final int num = 10;
        for(int i = 0;i < num;i++){
            for(int j = 0;j < num;j++){
                pdfContentByte.showTextAligned(Element.ALIGN_LEFT, watermarkText, 150 * i, 150 * j, 30);
            }
        }
        pdfContentByte.endText();
    }

    /**
     * @Description 添加图片水印
     * @Author sunshaoyu
     * @Date 2020/6/17-13:31
     * @param pdfWriter
     * @param imagePath
     */
    private void addWatermarkByImage(PdfWriter pdfWriter, String imagePath){
        try {
            Image image = Image.getInstance(imagePath);
            PdfContentByte content = pdfWriter.getDirectContentUnder();
            content.beginText();
            image.setAbsolutePosition(800, 600);

            final int len = 10;
            for(int i = 0;i < len;i++){
                for(int j = 0;j < len;j++){
                    content.addImage(image);
                }
            }
            content.endText();
        }catch (Exception e){
            throw new RuntimeException("Image watermark generation failed", e);
        }
    }

    /**
     * @Description 添加一级标题
     * @Author sunshaoyu
     * @Date 2020/6/17-13:15
     * @param document
     * @param title
     */
    protected void addDocumentTitle(Document document, String title) throws DocumentException {
        document.add(addNewLine(3));
        Paragraph topTitle = new Paragraph();
        topTitle.setAlignment(Element.ALIGN_CENTER);
        topTitle.add(new Phrase(title, new Font(baseFont, fontSizeTopTitle, Font.BOLD)));

        topTitle.add(addNewLine(3));
        document.add(topTitle);
    }

    /**
     * @Description 换行
     * @Author sunshaoyu
     * @Date 2020/6/17-13:25
     * @param lineNum
     * @return com.itextpdf.text.Chunk
     */
    private Chunk addNewLine(int lineNum){
        StringBuffer buffer = new StringBuffer();
        while(lineNum-- > 0){
            buffer.append("\n");
        }
        return new Chunk(buffer.toString());
    }

    @Override
    public void onOpenDocument(PdfWriter pdfWriter, Document document) {
        pdfTemplate = pdfWriter.getDirectContent().createTemplate(60, 60);
    }

    @Override
    public void onStartPage(PdfWriter pdfWriter, Document document) {

    }

    @Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        //每一页结束的时候写入页眉页脚
        addPageHeaderAndFooter(pdfWriter, document);
        //添加水印
        addWatermarkByText(pdfWriter, watermarkTitle);
    }

    @Override
    public void onCloseDocument(PdfWriter pdfWriter, Document document) {
        //关闭文档时,替换模板,部署页眉页脚组件
        pdfTemplate.beginText();
        pdfTemplate.setFontAndSize(baseFont, fontSizeBody);
        pdfTemplate.showText(String.format(" %d 页", pdfWriter.getPageNumber()));
        pdfTemplate.endText();
        pdfTemplate.closePath();
    }

    @Override
    public void onParagraph(PdfWriter pdfWriter, Document document, float v) {

    }

    @Override
    public void onParagraphEnd(PdfWriter pdfWriter, Document document, float v) {

    }

    @Override
    public void onChapter(PdfWriter pdfWriter, Document document, float v, Paragraph paragraph) {

    }

    @Override
    public void onChapterEnd(PdfWriter pdfWriter, Document document, float v) {

    }

    @Override
    public void onSection(PdfWriter pdfWriter, Document document, float v, int i, Paragraph paragraph) {

    }

    @Override
    public void onSectionEnd(PdfWriter pdfWriter, Document document, float v) {

    }

    @Override
    public void onGenericTag(PdfWriter pdfWriter, Document document, Rectangle rectangle, String s) {

    }

    public String getFontPath() {
        return fontPath;
    }

    public int getFontSizeHeader() {
        return fontSizeHeader;
    }

    public int getFontSizeBody() {
        return fontSizeBody;
    }

    public int getFontSizeWatermark() {
        return fontSizeWatermark;
    }

    public Rectangle getRectangleSize() {
        return rectangleSize;
    }

    public String getWatermarkTitle() {
        return watermarkTitle;
    }

    public String getPageHeaderTitle() {
        return pageHeaderTitle;
    }

    public BaseFont getBaseFont() {
        return baseFont;
    }

    public PdfTemplate getPdfTemplate() {
        return pdfTemplate;
    }
}
