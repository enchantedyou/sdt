package com.ssy.api.logic.builder;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_TRANKIND;
import com.ssy.api.entity.type.local.SdMetaGen;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.File;

/**
 * @Description 交易模型生成期
 * @Author sunshaoyu
 * @Date 2020年08月11日-10:00
 */
public class SdTrxnBuilder {

    private static final ThreadLocal<String> COMPLEX_PATH_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> SERVICE_PATH_LOCAL = new ThreadLocal<>();
    private static final String SPLIT_TOKEN = ";";

    /**
     * @Description 构建交易模型
     * @Author sunshaoyu
     * @Date 2020/8/11-14:18
     * @param metaGen
     */
    public static void build(SdMetaGen metaGen) {
        try{
            //构建复合类型
            Document complexDoc = buildComplex(metaGen);
            //构建服务类型
            Document serviceDoc = buildService(metaGen);
            //构建flowtran
            Document flowtranDoc = buildFlowtran(metaGen);

            //获取新flowtran的路径
            String oldFlowtranLocation = metaGen.getOriginalFlowtranId() + SdtConst.FLOWTRAN_SUFFIX;
            String newFlowtranPath = OdbFactory.searchFile(oldFlowtranLocation).getPath().split(oldFlowtranLocation)[0] + metaGen.getFlowtranId() + SdtConst.FLOWTRAN_SUFFIX;

            //写入文件
            XmlParser.writeXml(complexDoc, OdbFactory.searchFile(metaGen.getComplexLocation() + SdtConst.COMPLEX_SUFFIX).getPath());
            XmlParser.writeXml(serviceDoc, OdbFactory.searchFile(metaGen.getServiceLocation() + SdtConst.SERVICETYPE_SUFFIX).getPath());
            XmlParser.writeXml(flowtranDoc, newFlowtranPath);
        }catch (Exception e){
            throw new SdtException("Failed to build transaction model", e);
        }
    }

    /**
     * @Description 构建flowtran
     * @Author sunshaoyu
     * @Date 2020/8/11-14:10
     * @param metaGen
     * @return org.dom4j.Document
     */
    private static Document buildFlowtran(SdMetaGen metaGen) throws DocumentException {
        BizUtil.fieldNotNull(metaGen.getOriginalFlowtranId(), SdtDict.A.original_flowtran_id.getId(), SdtDict.A.original_flowtran_id.getLongName());
        BizUtil.fieldNotNull(metaGen.getFlowtranId(), SdtDict.A.flowtran_id.getId(), SdtDict.A.flowtran_id.getLongName());
        File flowtran = OdbFactory.searchFile(metaGen.getOriginalFlowtranId() + SdtConst.FLOWTRAN_SUFFIX);
        if(CommUtil.isNull(flowtran)){
            throw SdtServError.E0014(metaGen.getOriginalFlowtranId());
        }

        Document flowtranDocument = XmlParser.getXmlDocument(flowtran);
        addFlowtran(flowtranDocument.getRootElement(), metaGen);
        return flowtranDocument;
    }

    /**
     * @Description 构建服务
     * @Author sunshaoyu
     * @Date 2020/8/11-13:44
     * @param metaGen
     * @return org.dom4j.Document
     */
    private static Document buildService(SdMetaGen metaGen) throws DocumentException {
        BizUtil.fieldNotNull(metaGen.getServiceLocation(), SdtDict.A.service_location.getId(), SdtDict.A.service_location.getLongName());
        File service = OdbFactory.searchFile(metaGen.getServiceLocation() + SdtConst.SERVICETYPE_SUFFIX);
        if(CommUtil.isNull(service)){
            throw SdtServError.E0021(metaGen.getServiceLocation());
        }

        Document serviceDocument = XmlParser.getXmlDocument(service);
        addService(serviceDocument.getRootElement(), metaGen);
        return serviceDocument;
    }

    /**
     * @Description 构建复合类型
     * @Author sunshaoyu
     * @Date 2020/8/11-13:17
     * @param metaGen
     * @return org.dom4j.Element
     */
    private static Document buildComplex(SdMetaGen metaGen) throws DocumentException {
        BizUtil.fieldNotNull(metaGen.getComplexLocation(), SdtDict.A.complex_location.getId(), SdtDict.A.complex_location.getLongName());
        File complex = OdbFactory.searchFile(metaGen.getComplexLocation() + SdtConst.COMPLEX_SUFFIX);
        if(CommUtil.isNull(complex)){
            throw SdtServError.E0020(metaGen.getComplexLocation());
        }

        Document complexDocument = XmlParser.getXmlDocument(complex);
        addComplex(complexDocument.getRootElement(), String.format("%sIn", metaGen.getComplexId()), String.format("%s\tInput", metaGen.getLongname()), metaGen.getInputFields());
        addComplex(complexDocument.getRootElement(), String.format("%sOut", metaGen.getComplexId()), String.format("%s\tOutput", metaGen.getLongname()), metaGen.getOutputFields());
        return complexDocument;
    }

    /**
     * @Description 添加flowtran
     * @Author sunshaoyu
     * @Date 2020/8/11-14:10
     * @param flowtranRoot
     * @param metaGen
     */
    private static void addFlowtran(Element flowtranRoot, SdMetaGen metaGen){
        flowtranRoot.attribute("id").setValue(metaGen.getFlowtranId());
        flowtranRoot.attribute("longname").setValue(metaGen.getLongname());
        flowtranRoot.attribute("kind").setValue(metaGen.getTrxnKind().getValue());
        String packageAttr = flowtranRoot.element("interface").attributeValue("package");
        flowtranRoot.clearContent();

        Element flowtranInterface = flowtranRoot.addElement("interface");
        flowtranInterface.addAttribute("package", packageAttr);
        Element input = flowtranInterface.addElement("input");
        input.addAttribute("packMode", "true");

        for(String field : metaGen.getInputFields().split(SPLIT_TOKEN)){
            field = field.trim();
            if(CommUtil.isNull(field)){
                continue;
            }
            com.ssy.api.meta.defaults.Element e = OdbFactory.searchDict(field);
            addFlowtranFields(input, e);
        }

        if(CommUtil.isNotNull(metaGen.getOutputFields())){
            Element output = flowtranInterface.addElement("output");
            output.addAttribute("packMode", "true");
            output.addAttribute("asParm", "true");

            if(metaGen.getTrxnKind() == E_TRANKIND.Q){
                output = output.addElement("fields");
                output.addAttribute("id", "list01");
                output.addAttribute("scope", metaGen.getComplexLocation() + "." + metaGen.getComplexId() + "Out");
                output.addAttribute("required", "false");

                output.addAttribute("multi", "true");
                output.addAttribute("longname", metaGen.getLongname() + " output");
                output.addAttribute("array", "false");
            }
            for(String field : metaGen.getOutputFields().split(SPLIT_TOKEN)){
                field = field.trim();
                if(CommUtil.isNull(field)){
                    continue;
                }
                com.ssy.api.meta.defaults.Element e = OdbFactory.searchDict(field);
                addFlowtranFields(output, e);
            }
        }
        Element property = flowtranInterface.addElement("property");
        property.addAttribute("packMode", "true");
        Element printer = flowtranInterface.addElement("printer");
        printer.addAttribute("packMode", "true");

        Element flow = flowtranRoot.addElement("flow");
        Element service = flow.addElement("service");
        service.addAttribute("mappingToProperty", "false");
        service.addAttribute("id", metaGen.getServiceLocation() + "." + metaGen.getServiceId());
        service.addAttribute("serviceName", service.attributeValue("id"));
        service.addAttribute("longname", metaGen.getLongname());

        Element inMappings = service.addElement("in_mappings");
        inMappings.addAttribute("by_interface", "true");
        for(String field : metaGen.getInputFields().split(SPLIT_TOKEN)){
            if(CommUtil.isNull(field)){
                continue;
            }
            com.ssy.api.meta.defaults.Element e = OdbFactory.searchDict(field);
            Element mapping = inMappings.addElement("mapping");
            mapping.addAttribute("src", e.getId());
            mapping.addAttribute("dest", metaGen.getValName() + "In." + e.getId());
            mapping.addAttribute("by_interface", "true");
            mapping.addAttribute("on_top", "true");
        }

        Element outMappings = service.addElement("out_mappings");
        outMappings.addAttribute("by_interface", "true");
        if(CommUtil.isNotNull(metaGen.getOutputFields())){
            for(String field : metaGen.getInputFields().split(SPLIT_TOKEN)) {
                if (CommUtil.isNull(field)) {
                    continue;
                }
                com.ssy.api.meta.defaults.Element e = OdbFactory.searchDict(field);
                Element mapping = outMappings.addElement("mapping");
                mapping.addAttribute("src", metaGen.getTrxnKind() == E_TRANKIND.Q ? "list01" : metaGen.getValName() + "Out." + e.getId());
                mapping.addAttribute("dest", metaGen.getTrxnKind() == E_TRANKIND.Q ? "list01" : e.getId());
                mapping.addAttribute("by_interface", "true");
                mapping.addAttribute("on_top", "true");

                if(metaGen.getTrxnKind() == E_TRANKIND.Q){
                    break;
                }
            }
        }
        flowtranRoot.addElement("outMapping");
        flowtranRoot.addElement("propertyToPrinterMapping");
        flowtranRoot.addElement("outToPrinterMapping");
    }

    /**
     * @Description 添加flowtran输入输出字段
     * @Author sunshaoyu
     * @Date 2020/8/11-13:56
     * @param input
     * @param e
     */
    private static void addFlowtranFields(Element input, com.ssy.api.meta.defaults.Element e) {
        if(null == e){
            return;
        }
        Element field = input.addElement("field");
        field.addAttribute("id", e.getId());
        field.addAttribute("type", e.getType().getFullId());
        field.addAttribute("required", String.valueOf(e.isRequired()));
        field.addAttribute("multi", String.valueOf(e.isMulti()));
        field.addAttribute("array", String.valueOf(e.isArray()));
        field.addAttribute("longname", e.getLongName());
        field.addAttribute("ref", e.getRef());
        field.addAttribute("desc", e.getDesc());
    }

    /**
     * @Description 添加服务
     * @Author sunshaoyu
     * @Date 2020/8/11-13:42
     * @param serviceRoot
     * @param metaGen
     */
    private static void addService(Element serviceRoot, SdMetaGen metaGen){
        Element addServiceType = serviceRoot.addElement("service");
        addServiceType.addAttribute("id", metaGen.getServiceId());
        addServiceType.addAttribute("name", metaGen.getServiceId());
        addServiceType.addAttribute("longname", metaGen.getLongname());

        Element addInterface = addServiceType.addElement("interface");
        Element input = addInterface.addElement("input");
        input.addAttribute("packMode", "false");
        Element inputField = input.addElement("field");

        inputField.addAttribute("id", metaGen.getValName() + "In");
        inputField.addAttribute("type", metaGen.getComplexLocation() + "." + metaGen.getComplexId() + "In");
        inputField.addAttribute("required", "false");
        inputField.addAttribute("multi", "false");
        inputField.addAttribute("array", "false");
        inputField.addAttribute("longname", metaGen.getLongname() + " input");

        Element output = addInterface.addElement("output");
        output.addAttribute("packMode", "false");
        output.addAttribute("asParm", "false");

        if(CommUtil.isNotNull(metaGen.getOutputFields())){
            Element outputField = output.addElement("field");
            outputField.addAttribute("id", metaGen.getTrxnKind() == E_TRANKIND.Q ? "list01" : metaGen.getValName() + "Out");
            outputField.addAttribute("type", metaGen.getComplexLocation() + "." + metaGen.getComplexId() + "Out");
            outputField.addAttribute("required", "false");
            outputField.addAttribute("multi", String.valueOf(metaGen.getTrxnKind() == E_TRANKIND.Q));
            outputField.addAttribute("array", "false");
            outputField.addAttribute("longname", metaGen.getLongname() + " output");
        }

        Element property = addInterface.addElement("property");
        property.addAttribute("packMode", "false");
        Element printer = addInterface.addElement("printer");
        printer.addAttribute("packMode", "true");
    }

    /**
     * @Description
     * @Author sunshaoyu
     * @Date 2020/8/11-13:15
     * @param complexRoot
     * @param id
     * @param longName
     * @param fields    添加复合类型
     */
    private static void addComplex(Element complexRoot, String id, String longName, String fields){
        if(CommUtil.isNotNull(fields)){
            Element addComplexType = complexRoot.addElement("complexType");
            addComplexType.addAttribute("id", id);
            addComplexType.addAttribute("longname", longName);
            addComplexType.addAttribute("dict", "false");
            addComplexType.addAttribute("abstract", "false");
            addComplexType.addAttribute("introduct", "false");

            String[] array = fields.split(SPLIT_TOKEN);
            for(String field : array){
                field = field.trim();
                if(CommUtil.isNull(field)) {
                    continue;
                }

                com.ssy.api.meta.defaults.Element e = OdbFactory.searchDict(field);
                if(null == e){
                    continue;
                }
                Element addComplexTypeElement = addComplexType.addElement("element");
                addComplexTypeElement.addAttribute("id", e.getId());
                addComplexTypeElement.addAttribute("longname", e.getLongName());

                addComplexTypeElement.addAttribute("type", e.getType().getFullId());
                addComplexTypeElement.addAttribute("ref", e.getRef());
                addComplexTypeElement.addAttribute("required", String.valueOf(e.isRequired()));
                addComplexTypeElement.addAttribute("desc", e.getDesc());

                addComplexTypeElement.addAttribute("multi", String.valueOf(e.isMulti()));
                addComplexTypeElement.addAttribute("range", String.valueOf(e.isRange()));
                addComplexTypeElement.addAttribute("array", String.valueOf(e.isArray()));
                addComplexTypeElement.addAttribute("final", String.valueOf(e.isFinal()));
                addComplexTypeElement.addAttribute("override", String.valueOf(e.isOverride()));
                addComplexTypeElement.addAttribute("allowSubType", String.valueOf(e.isAllowSubType()));
            }
        }
    }
}
