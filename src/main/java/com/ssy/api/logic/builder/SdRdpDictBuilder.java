package com.ssy.api.logic.builder;

import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.rdp.RdpDict;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description rdp字典构建
 * @Author sunshaoyu
 * @Date 2020/11/2-13:53
 */
@Slf4j
public class SdRdpDictBuilder {

    /**
     * @Description 构建rdp字典
     * @Author sunshaoyu
     * @Date 2020/11/2-14:10
     * @param locationPrefix
     * @param outputPath
     */
    public static void build(String locationPrefix, String outputPath){
        try{
            if(CommUtil.isNull(locationPrefix) || locationPrefix.length() < 2){
                return;
            }

            Set<com.ssy.api.meta.defaults.Element> dictSet = new HashSet<>();
            Map<String, com.ssy.api.meta.defaults.Element> dictMap = OdbFactory.getDictMap();
            for(Map.Entry<String, com.ssy.api.meta.defaults.Element> entry : dictMap.entrySet()){
                com.ssy.api.meta.defaults.Element dict = entry.getValue();
                if(dict.getLocation().toLowerCase().startsWith(locationPrefix.toLowerCase())){
                    dictSet.add(entry.getValue());
                }
            }

            Document document = XmlParser.getXmlDocument(SdRdpDictBuilder.class.getResource("/templates/xml/rdp.dict.xml").getFile());
            Element rootElement = document.getRootElement();
            //根节点属性赋值
            locationPrefix = locationPrefix.substring(0, 1).toUpperCase() + locationPrefix.substring(1);
            rootElement.attribute("id").setValue(locationPrefix + "Dict");
            rootElement.attribute("desc").setValue("default description");
            rootElement.attribute("name").setValue("default longname");

            for(com.ssy.api.meta.defaults.Element dict : dictSet){
                appendDictNode(locationPrefix, dict, rootElement);
            }

            rootElement.elements().remove(0);
            XmlParser.writeXml(document, outputPath + File.separator + String.format("%sDict.dict.xml", locationPrefix));
        }catch (Exception e){
            throw new SdtException(e);
        }
    }

    private static void appendDictNode(String locationPrefix, com.ssy.api.meta.defaults.Element dict, Element rootElement) throws IOException, DocumentException {
        Element dictElement = rootElement.element("dict").createCopy();
        if(null == dict.getType()){
            return;
        }
        resolveDictAttr(dictElement, convertToRdpDict(locationPrefix, dict), dict.getType().getRestriction() == E_RESTRICTION.ENUMTYPE);
        rootElement.add(dictElement);
    }

    private static void resolveDictAttr(Element domEle, RdpDict rdpDict, boolean isContainsList){
        domEle.attribute("id").setValue(rdpDict.getId());
        domEle.attribute("name").setValue(rdpDict.getName());
        domEle.attribute("type").setValue(rdpDict.getType());
        domEle.attribute("base").setValue(rdpDict.getBase());
        domEle.attribute("length").setValue(rdpDict.getLength());
        domEle.attribute("dotnum").setValue(rdpDict.getDotnum());
        domEle.attribute("inputtype").setValue(rdpDict.getInputtype());

        if(isContainsList){
            domEle.attribute("listpath").setValue(rdpDict.getListpath());
        }else{
            domEle.remove(domEle.attribute("listpath"));
        }
    }

    /**
     * @Description 后端字典转rdp字典
     * @Author sunshaoyu
     * @Date 2020/11/2-14:00
     * @param locationPrefix
     * @param dict
     * @return com.ssy.api.meta.rdp.RdpDict
     */
    private static RdpDict convertToRdpDict(String locationPrefix, com.ssy.api.meta.defaults.Element dict){
        AbstractRestrictionType dictType = dict.getType();
        String dictId = dict.getRef().replaceAll("\\.", "_");
        dictId = locationPrefix + "Dict" + dictId.substring(dictId.indexOf("_"));
        RdpDict.Builder rdpDictBuilder = new RdpDict.Builder(dictId, dict.getDesc(), dictType.getId());

        //字典包含下拉列表
        StringBuilder basePath = new StringBuilder("RdpCoreListSys/list/");
        if(dictType.getRestriction() == E_RESTRICTION.ENUMTYPE){
            rdpDictBuilder.dictWithList(basePath.append(locationPrefix).append("EnumType").append("/").append(dictType.getId()).append(".list.xml").toString());
        }
        return rdpDictBuilder.build();
    }
}
