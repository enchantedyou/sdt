package com.ssy.api.utils;

import com.ssy.api.entity.constant.SdtConst;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description xml工具类
 * @Author sunshaoyu
 * @Date 2020年06月11日-13:23
 */
public class XmlUtil {

    /**
     * @Description 从文件中获取xml文件的document
     * @Author sunshaoyu
     * @Date 2020/6/11-13:52
     * @param xmlPath
     * @return org.dom4j.Document
     */
    public static Document getXmlDocument(String xmlPath) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(new File(xmlPath)).getDocument();
    }

    /**
     * @Description 从文件中获取xml文件的document
     * @Author sunshaoyu
     * @Date 2020/6/12-22:47
     * @param file
     * @return org.dom4j.Document
     */
    public static Document getXmlDocument(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(file).getDocument();
    }

    /**
     * @Description 从文件中获取xml文件的根节点
     * @Author sunshaoyu
     * @Date 2020/6/11-13:38
     * @param xmlPath   xml文件路径
     * @return org.dom4j.Element
     */
    public static Element getXmlRootElement(String xmlPath) throws DocumentException {
        return getXmlDocument(xmlPath).getRootElement();
    }

    /**
     * @Description 从文件中获取xml文件的根节点
     * @Author sunshaoyu
     * @Date 2020/6/12-22:48
     * @param file
     * @return org.dom4j.Element
     */
    public static Element getXmlRootElement(File file) throws DocumentException {
        return getXmlDocument(file).getRootElement();
    }

    /**
     * @Description 从url中获取xml文件的根节点
     * @Author sunshaoyu
     * @Date 2020/6/11-13:45
     * @param url   链接地址
     * @return org.dom4j.Element
     */
    public static Element getUrlRootElement(String url) throws MalformedURLException, DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(new URL(url)).getRootElement();
    }

    /**
     * @Description 写入xml文件
     * @Author sunshaoyu
     * @Date 2020/6/11-14:03
     * @param doc   xml文件对象的document
     * @param outputPath    输出文件路径
     */
    public static void writeXml(Document doc, String outputPath) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(SdtConst.DEFAULT_ENCODING);
        format.setIndent("\t");
        File xmlFile = new File(outputPath);

        if(!xmlFile.exists()){
            xmlFile.createNewFile();
        }

        XMLWriter writer = null;
        try{
            writer = new XMLWriter(new FileOutputStream(xmlFile), format);
            writer.write(doc);
        }finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * @Description 递归搜索xml元素节点,返回满足条件的第一个节点或元素
     * @Author sunshaoyu
     * @Date 2020/6/11-15:54
     * @param element
     * @param elementName
     * @param attrName
     * @param attrValue
     * @return org.dom4j.Element
     */
    public static Element searchXmlElement(Element element,String elementName,String attrName,String attrValue){
        if(CommUtil.isNull(elementName) || CommUtil.isNull(element)){
            throw new IllegalArgumentException("Element entity or element name cannot be null or empty");
        }else if((CommUtil.isNull(attrName) || CommUtil.isNull(attrValue)) && elementName.equals(element.getName())){
            return element;
        }else if(elementName.equals(element.getName()) && attrValue.equals(element.attributeValue(attrName))){
            return element;
        }else{
            List<Element> elementList = element.elements();
            if(CommUtil.isNotNull(elementList)) {
                for(Element subElement : elementList){
                    Element resultElement = searchXmlElement(subElement, elementName, attrName, attrValue);
                    if(CommUtil.isNotNull(resultElement)){
                        return resultElement;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @Description 归搜索xml元素节点,返回满足条件的第一个节点或元素
     * @Author sunshaoyu
     * @Date 2020/6/11-15:57
     * @param element
     * @param elementName
     * @return org.dom4j.Element
     */
    public static Element searchXmlElement(Element element,String elementName){
        return searchXmlElement(element, elementName, null, null);
    }

    /**
     * @Description 递归搜索xml元素节点,返回满足条件的节点的子节点列表
     * @Author sunshaoyu
     * @Date 2020/6/11-15:58
     * @param element
     * @param elementName
     * @param attrName
     * @param attrValue
     * @return java.util.List<org.dom4j.Element>
     */
    public static List<Element> searchXmlElementList(Element element,String elementName,String attrName,String attrValue){
        if(CommUtil.isNull(elementName) || CommUtil.isNull(element)){
            throw new IllegalArgumentException("Element entity or element name cannot be null or empty");
        }else if((CommUtil.isNull(attrName) || CommUtil.isNull(attrValue)) && elementName.equals(element.getName())){
            return element.elements();
        }else if(elementName.equals(element.getName()) && attrValue.equals(element.attributeValue(attrName))){
            return element.elements();
        }else{
            List<Element> elementList = element.elements();
            if(CommUtil.isNotNull(elementList)){
                for(Element subElement : elementList){
                    List<Element> resultElementList = searchXmlElementList(subElement, elementName,attrName,attrValue);
                    if(CommUtil.isNotNull(resultElementList)){
                        return resultElementList;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @Description 递归搜索xml元素节点,返回满足条件的节点的子节点列表
     * @Author sunshaoyu
     * @Date 2020/6/11-15:58
     * @param element
     * @param elementName
     * @return java.util.List<org.dom4j.Element>
     */
    public static List<Element> searchXmlElementList(Element element,String elementName){
        return searchXmlElementList(element, elementName, null, null);
    }

    /**
     * @Description 查询根元素element下所有元素名为elementName的元素
     * @Author sunshaoyu
     * @Date 2020/6/11-15:59
     * @param list
     * @param element
     * @param elementName
     * @return java.util.List<org.dom4j.Element>
     */
    private static List<Element> searchTargetAllXmlElement(List<Element> list,Element element,String elementName){
        if(CommUtil.isNull(elementName) || CommUtil.isNull(element)){
            throw new IllegalArgumentException("Element entity or element name cannot be null or empty");
        }else if(CommUtil.isNull(list)){
            list = new LinkedList<Element>();
        }

        List<Element> elementList = element.elements();
        if(CommUtil.isNotNull(elementList)){
            for(Element e : elementList){
                if(e.getName().equals(elementName)){
                    list.add(e);
                }else{
                    list = searchTargetAllXmlElement(list, e, elementName);
                }
            }
        }
        return list;
    }

    /**
     * @Description 查询根元素element下所有元素名为elementName的元素
     * @Author sunshaoyu
     * @Date 2020/6/11-16:00
     * @param element
     * @param elementName
     * @return java.util.List<org.dom4j.Element>
     */
    public static List<Element> searchTargetAllXmlElement(Element element,String elementName){
        return searchTargetAllXmlElement(null, element, elementName);
    }

    /**
     * @Description 序列化xml
     * @Author sunshaoyu
     * @Date 2020/6/30-10:55
     * @param entity
     * @return java.lang.String
     */
    public static <T> String serializeXML(T entity) throws JAXBException, IOException {
        StringWriter stringWriter = new StringWriter();
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(entity, stringWriter);
            return stringWriter.toString();
        }finally {
            stringWriter.close();
        }
    }

    /**
     * @Description 反序列化xml
     * @Author sunshaoyu
     * @Date 2020/7/1-17:32
     * @param xml
     * @param clazz
     * @return T
     */
    public static <T> T deserializeXML(String xml, Class<T> clazz) throws JAXBException, XMLStreamException {
        XMLStreamReader xmlStreamReader = null;
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();

            xmlStreamReader = xmlInputFactory.createXMLStreamReader(reader);
            return unmarshaller.unmarshal(xmlStreamReader, clazz).getValue();
        } finally {
            if(xmlStreamReader != null){
                xmlStreamReader.close();
            }
        }
    }
}
