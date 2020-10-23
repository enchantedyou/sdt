package com.ssy.api.logic.local;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_TRANINTFTYPE;
import com.ssy.api.entity.enums.E_TRANKIND;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfAssemble;
import com.ssy.api.meta.flowtran.IntfFields;
import com.ssy.api.meta.flowtran.IntfService;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.RedisHelper;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 交易flowtran解析器
 * @Author sunshaoyu
 * @Date 2020年07月22日-17:09
 */
@Component
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
            //优先从redis缓存中获取
            if(RedisHelper.hasKey(flowTranId)){
                return (Flowtran) RedisHelper.getValue(flowTranId);
            }

            String fileName = flowTranId + SdtConst.FLOWTRAN_SUFFIX;
            File flowtranFile = OdbFactory.searchFile(fileName);
            if(CommUtil.isNull(flowtranFile)){
                throw SdtServError.E0014(flowTranId);
            }

            Element rootNode = XmlParser.getXmlRootElement(flowtranFile);
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
            //存入redis缓存
            RedisHelper.addAndSetValue(flowTranId, flowtran, SdtConst.REDIS_FLOWTRAN_TIMEOUT);
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
        List<IntfFields> intfFieldsList = new ArrayList<>();
        intfAssemble.setAsParm(Boolean.valueOf(intfNode.attributeValue("asParm")));
        intfAssemble.setPackMode(Boolean.valueOf(intfNode.attributeValue("packMode")));

        //解析field
        List<com.ssy.api.meta.defaults.Element> fieldList = parseField(intfFieldsList, flowtran, intfNode);

        //解析fields
        List<Element> fieldsNodeList = intfNode.elements("fields");
        for(Element e : fieldsNodeList){
            parseFields(flowtran, intfFieldsList, e);
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
     * @Description 解析fields
     * @Author sunshaoyu
     * @Date 2020/9/10-10:20
     * @param flowtran
     * @param intfFieldsList
     * @param e
     */
    private static void parseFields(Flowtran flowtran, List<IntfFields> intfFieldsList, Element e) {
        IntfFields intfFields = new IntfFields(flowtran.getLocation(), e.attributeValue("id"), e.attributeValue("longname"));
        if(CommUtil.equals(e.getName(), "field")){
            intfFields.setScope(e.attributeValue("type"));
            ComplexType complex = OdbFactory.searchComplexType(intfFields.getScope().split("\\.")[1]);
            if(CommUtil.isNotNull(complex)){
                List<com.ssy.api.meta.defaults.Element> elementList = new ArrayList<>();
                complex.getElementMap().forEach((key, element) -> {
                    elementList.add(element);
                });
                intfFields.setSubFieldList(elementList);
            }
        }else if(CommUtil.equals(e.getName(), "fields")){
            intfFields.setScope(e.attributeValue("scope"));
            intfFields.setSubFieldList(parseField(intfFieldsList, flowtran, e));
        }
        intfFields.setMulti(Boolean.valueOf(e.attributeValue("multi")));
        intfFields.setRequired(Boolean.valueOf(e.attributeValue("required")));
        intfFieldsList.add(intfFields);
    }

    /**
     * @Description 解析field
     * @Author sunshaoyu
     * @Date 2020/7/23-11:38
     * @param intfFieldsList
     * @param flowtran
     * @param node
     * @return java.util.List<com.ssy.api.meta.defaults.Element>
     */
    private static List<com.ssy.api.meta.defaults.Element> parseField(List<IntfFields> intfFieldsList, Flowtran flowtran, Element node) {
        List<Element> fieldNodeList = node.elements("field");
        List<com.ssy.api.meta.defaults.Element> fieldList = new ArrayList<>();
        for(Element e : fieldNodeList){
            com.ssy.api.meta.defaults.Element dict = OdbFactory.searchDict(e.attributeValue("id"));
            if(null == dict && CommUtil.equals("true", e.attributeValue("multi"))){
                parseFields(flowtran, intfFieldsList, e);
            }else{
                fieldList.add(dict);
            }
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
    private static void parseService(Flowtran flowtran, Element rootNode) throws DocumentException {
        List<Element> serviceNodeList = XmlParser.searchTargetAllXmlElement(rootNode, "service");
        List<IntfService> serviceList = new ArrayList<>();
        for(Element e : serviceNodeList){
            IntfService service = new IntfService(flowtran.getLocation(), e.attributeValue("id"), e.attributeValue("longname"));
            service.setMappingToProperty(Boolean.valueOf(e.attributeValue("mappingToProperty")));
            service.setServiceName(e.attributeValue("serviceName"));
            service.setTest(e.attributeValue("test"));

            //解析输入输出接口
            String[] serviceComponent = service.getServiceName().split("\\.");
            File serviceTypeFile = OdbFactory.searchFile(serviceComponent[0] + SdtConst.SERVICETYPE_SUFFIX);
            Element serviceRoot = XmlParser.getXmlRootElement(serviceTypeFile);

            List<com.ssy.api.meta.defaults.Element> inputList = new ArrayList<>();
            List<com.ssy.api.meta.defaults.Element> outputList = new ArrayList<>();
            List<Element> serviceTypeNodeList = XmlParser.searchTargetAllXmlElement(serviceRoot, "service");
            for(Element serviceNode : serviceTypeNodeList){
                if(CommUtil.equals(serviceNode.attributeValue("id"), serviceComponent[1])){
                    //输入接口
                    parseServiceIntfField("input", inputList, serviceNode);
                    service.setServiceInput(inputList);

                    //输出接口
                    parseServiceIntfField("output", outputList, serviceNode);
                    service.setServiceOutput(outputList);
                }
            }
            serviceList.add(service);
        }
        flowtran.setServiceList(serviceList);
    }

    /**
     * @Description 解析服务接口字段
     * @Author sunshaoyu
     * @Date 2020/10/23-14:39
     * @param intfName
     * @param fieldList
     * @param serviceNode
     */
    private static void parseServiceIntfField(String intfName, List<com.ssy.api.meta.defaults.Element> fieldList, Element serviceNode) {
        List<Element> input = serviceNode.element("interface").element(intfName).elements();
        for(Element i : input){
            com.ssy.api.meta.defaults.Element dict = OdbFactory.searchDict(i.attributeValue("id"));
            if(CommUtil.isNull(dict)){
                String[] typeComponent = i.attributeValue("type").split("\\.");
                com.ssy.api.meta.defaults.Element e = new com.ssy.api.meta.defaults.Element(null, i.attributeValue("id"), null, OdbFactory.searchComplexType(typeComponent[0], typeComponent[1]), null, null);
                e.setMulti(CommUtil.equals(String.valueOf(i.attributeValue("multi")), "true"));
                fieldList.add(e);
            }else{
                fieldList.add(dict);
            }
        }
    }
}