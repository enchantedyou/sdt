package com.ssy.api.logic.builder;

import com.ssy.api.dao.mapper.ct.SmpSysDictMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.table.ct.SmpSysDict;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.meta.rdp.RdpListType;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Description rdp下拉列表构建
 * @Author sunshaoyu
 * @Date 2020年10月30日-14:13
 */
@Slf4j
@Component
public class SdRdpEnumBuilder {

    private static SmpSysDictMapper sysDictMapper;
    private static SdtContextConfig contextConfig;

    @Autowired
    public void setSysDictMapper(SmpSysDictMapper sysDictMapper) {
        SdRdpEnumBuilder.sysDictMapper = sysDictMapper;
    }

    @Autowired
    public void setContextConfig(SdtContextConfig contextConfig) {
        SdRdpEnumBuilder.contextConfig = contextConfig;
    }

    /**
     * @Description 构建下拉列表
     * @Author sunshaoyu
     * @Date 2020/10/30-14:47
     * @param locationPrefix
     * @param outputPath
     * @return java.util.List<com.ssy.api.meta.rdp.RdpListType>
     */
    public static void build(String locationPrefix, String outputPath){
        try{
            DBContextHolder.switchToDataSource(contextConfig.getSumpDataSource());
            Set<AbstractRestrictionType> enumSet = new HashSet<>();
            Map<String, AbstractRestrictionType> restrictionTypeMap = OdbFactory.getEnumMap();

            for(Map.Entry<String, AbstractRestrictionType> entry : restrictionTypeMap.entrySet()){
                AbstractRestrictionType enumType = entry.getValue();
                if(enumType.getLocation().toLowerCase().startsWith(locationPrefix.toLowerCase())){
                    enumSet.add(enumType);
                }
            }

            for(AbstractRestrictionType enumType : enumSet){
                buildXml(convertToRdpListType(enumType.getId(), enumType), outputPath);
            }
        }catch (Exception e){
            throw new SdtException(e);
        }
    }

    private static void buildXml(RdpListType rdpListType, String outputPath) throws IOException, DocumentException {
        Document document = XmlParser.getXmlDocument(SdRdpEnumBuilder.class.getResource("/templates/xml/rdp.list.xml").getFile());
        Element rootElement = document.getRootElement();
        resolveListAttr(rootElement, rdpListType);

        List<RdpListType.Item> itemList = rdpListType.getItemList();
        Collections.sort(itemList, (e1, e2) -> {
            return CommUtil.compare(e1.getValue(), e2.getValue());
        });

        for(RdpListType.Item item : itemList){
            Element itemElement = rootElement.element("item").createCopy();
            resolveItemAttr(itemElement, item);
            rootElement.add(itemElement);
        }
        rootElement.elements().remove(0);
        XmlParser.writeXml(document, outputPath + File.separator + String.format("%s.list.xml", rdpListType.getId()));
    }

    private static void resolveListAttr(Element element, RdpListType rdpListType){
        element.attribute("id").setValue(rdpListType.getId());
        element.attribute("type").setValue(rdpListType.getType());
        element.attribute("name").setValue(rdpListType.getName());
        element.attribute("base").setValue(rdpListType.getBase());
        element.attribute("nullflag").setValue(rdpListType.getNullflag());
        element.attribute("valuedisplayflag").setValue(rdpListType.getValuedisplayflag());
        element.attribute("sql").setValue(rdpListType.getSql());
        element.attribute("displayfield").setValue(rdpListType.getDisplayfield());
    }

    private static void resolveItemAttr(Element element, RdpListType.Item item){
        element.attribute("id").setValue(item.getId());
        element.attribute("value").setValue(item.getValue());
        element.attribute("desc").setValue(item.getDesc());
        element.attribute("display").setValue(item.getDisplay());
    }

    /**
     * @Description 后端枚举转前端下拉类型
     * @Author sunshaoyu
     * @Date 2020/10/30-14:47
     * @param id
     * @param restrictionType
     * @return com.ssy.api.meta.rdp.RdpListType
     */
    private static RdpListType convertToRdpListType(String id, AbstractRestrictionType restrictionType){
        RdpListType rdpListType = new RdpListType.Builder(id, "C", restrictionType.getLongName(), restrictionType.getBase().getValue()).build();
        Map<String, DefaultEnumerationType> enumMap = restrictionType.getEnumerationMap();

        for(Map.Entry<String, DefaultEnumerationType> entry : enumMap.entrySet()){
            DefaultEnumerationType enumType = entry.getValue();
            SmpSysDict sysDict = sysDictMapper.selectByPrimaryKey(restrictionType.getId(), enumType.getValue());
            String dictName = CommUtil.isNull(sysDict) ? enumType.getLongName() : sysDict.getDictName();
            rdpListType.addItem(enumType.getId(), enumType.getValue(), dictName, dictName);
        }
        return rdpListType;
    }
}
