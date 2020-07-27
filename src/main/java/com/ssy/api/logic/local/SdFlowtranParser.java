package com.ssy.api.logic.local;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_TRANINTFTYPE;
import com.ssy.api.entity.enums.E_TRANKIND;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfAssemble;
import com.ssy.api.meta.flowtran.IntfFields;
import com.ssy.api.meta.flowtran.IntfService;
import com.ssy.api.utils.parse.XmlUtil;
import com.ssy.api.utils.system.CommUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 交易flowtran解析器
 * @Author sunshaoyu
 * @Date 2020年07月22日-17:09
 */
public class SdFlowtranParser {

    /**
     * @Description 加载并解析flowtran
     * @Author sunshaoyu
     * @Date 2020/7/23-13:09
     * @param flowTranId
     * @return com.ssy.api.meta.flowtran.Flowtran
     */
    public static Flowtran load(String flowTranId){
        try {
            String fileName = flowTranId + SdtConst.FLOWTRAN_SUFFIX;
            File flowtranFile = OdbFactory.searchFile(fileName);
            if(CommUtil.isNull(flowtranFile)){
                throw SdtServError.E0014(flowTranId);
            }

            Element rootNode = XmlUtil.getXmlRootElement(flowtranFile);
            Flowtran flowtran = new Flowtran(fileName, flowTranId, rootNode.attributeValue("longname"));

            //解析基础属性
            String kind = rootNode.attributeValue("kind");
            flowtran.setKind(CommUtil.isNull(kind) ? null : E_TRANKIND.valueOf(kind));
            flowtran.setTranPackage(rootNode.attributeValue("package"));

            //解析接口
            List<Element> intfNodeList = rootNode.element("interface").elements();
            for(Element e : intfNodeList){
                parseIntf(flowtran, E_TRANINTFTYPE.valueOf(e.getName()), e);
            }

            //解析服务
            parseService(flowtran, rootNode);
            return flowtran;
        } catch (DocumentException e) {
            throw new SdtException("Failed to parse flowtran " + flowTranId, e);
        }
    }

    /**
     * @Description 解析接口的输入、输出、属性、打印字段
     * @Author sunshaoyu
     * @Date 2020/7/23-12:26
     * @param flowtran
     * @param interfaceType
     * @param intfNode
     */
    private static void parseIntf(Flowtran flowtran, E_TRANINTFTYPE interfaceType, Element intfNode){
        IntfAssemble intfAssemble = new IntfAssemble();
        intfAssemble.setAsParm(Boolean.valueOf(intfNode.attributeValue("asParm")));
        intfAssemble.setPackMode(Boolean.valueOf(intfNode.attributeValue("packMode")));
        //解析field
        List<com.ssy.api.meta.defaults.Element> fieldList = parseField(intfNode);

        //解析fields
        List<Element> fieldsNodeList = intfNode.elements("fields");
        List<IntfFields> intfFieldsList = new ArrayList<>();
        for(Element e : fieldsNodeList){
            IntfFields intfFields = new IntfFields(flowtran.getLocation(), e.attributeValue("id"), e.attributeValue("longname"));
            intfFields.setScope(e.attributeValue("scope"));
            intfFields.setMulti(Boolean.valueOf(e.attributeValue("multi")));
            intfFields.setRequired(Boolean.valueOf(e.attributeValue("required")));

            intfFields.setSubFieldList(parseField(e));
            intfFieldsList.add(intfFields);
        }
        intfAssemble.setFieldList(fieldList);
        intfAssemble.setFieldsList(intfFieldsList);

        switch (interfaceType){
            case input:
                flowtran.setInput(intfAssemble);break;
            case output:
                flowtran.setOutput(intfAssemble);break;
            case property:
                flowtran.setProperty(intfAssemble);break;
            case printer:
                flowtran.setPrinter(intfAssemble);break;
        }
    }

    /**
     * @Description 解析field
     * @Author sunshaoyu
     * @Date 2020/7/23-11:38
     * @param node
     * @return java.util.List<com.ssy.api.meta.defaults.Element>
     */
    private static List<com.ssy.api.meta.defaults.Element> parseField(Element node) {
        List<Element> fieldNodeList = node.elements("field");
        List<com.ssy.api.meta.defaults.Element> fieldList = new ArrayList<>();
        for(Element e : fieldNodeList){
            fieldList.add(OdbFactory.searchDict(e.attributeValue("id")));
        }
        return fieldList;
    }

    /**
     * @Description 解析服务列表
     * @Author sunshaoyu
     * @Date 2020/7/23-13:04
     * @param flowtran
     * @param rootNode
     */
    private static void parseService(Flowtran flowtran, Element rootNode){
        List<Element> serviceNodeList = XmlUtil.searchTargetAllXmlElement(rootNode, "service");
        List<IntfService> serviceList = new ArrayList<>();
        for(Element e : serviceNodeList){
            IntfService service = new IntfService(flowtran.getLocation(), e.attributeValue("id"), e.attributeValue("longname"));
            service.setMappingToProperty(Boolean.valueOf(e.attributeValue("mappingToProperty")));
            service.setServiceName(e.attributeValue("serviceName"));
            service.setTest(e.attributeValue("test"));
            serviceList.add(service);
        }
        flowtran.setServiceList(serviceList);
    }
}