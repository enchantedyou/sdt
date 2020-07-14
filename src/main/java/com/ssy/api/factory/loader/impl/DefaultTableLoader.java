package com.ssy.api.factory.loader.impl;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.factory.loader.TableTypeLoader;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.TableType;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.parse.XmlUtil;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 默认表模型加载器
 * @Author sunshaoyu
 * @Date 2020年06月14日-13:33
 */
@Component
public class DefaultTableLoader implements TableTypeLoader {

    @Override
    public Map<String, TableType> load(Map<String, File> fileMap) {
        Map<String, TableType> map = new ConcurrentHashMap<>();
        for(String fileName : fileMap.keySet()) {
            if (fileName.contains(SdtConst.TABLE_SUFFIX)) {
                try {
                    Element rootNode = XmlUtil.getXmlRootElement(fileMap.get(fileName));
                    String location = rootNode.attributeValue("id");
                    List<Element> tableList = XmlUtil.searchTargetAllXmlElement(rootNode, SdtConst.TABLE_NODE_NAME);

                    for(Element table : tableList){
                        String tableId = table.attributeValue("id");
                        map.put(tableId, new TableType(
                                location, tableId, table.attributeValue("longname"), table.attributeValue("name"), table.attributeValue("tableType"),
                                table.attributeValue("category"), table.attributeValue("extension"), getTableFiledMap(location, table)
                        ));
                    }
                } catch (Exception e) {
                    ApPubErr.E0007(e);
                }
            }
        }
        return map;
    }

    /**
     * @Description 获取表模型的字段列表
     * @Author sunshaoyu
     * @Date 2020/6/14-14:06
     * @param location
     * @param table
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.Element>
     */
    private Map<String, com.ssy.api.meta.defaults.Element> getTableFiledMap(String location, Element table) {
        List<Element> fieldList = XmlUtil.searchTargetAllXmlElement(table, SdtConst.FIELD_NODE_NAME);
        Map<String, com.ssy.api.meta.defaults.Element> fieldMap = new HashMap<>();
        for(Element field : fieldList){
            fieldMap.put(field.attributeValue("id"), new com.ssy.api.meta.defaults.Element(
                location, field.attributeValue("id"), field.attributeValue("longname"),
                    getElementRestrictionType(field.attributeValue("type")), field.attributeValue("desc"), field.attributeValue("ref")
            ));
        }
        return fieldMap;
    }


    /**
     * @Description 获取复合类型中每个元素的限制类型
     * @Author sunshaoyu
     * @Date 2020/6/13-14:45
     * @param type
     * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
     */
    private AbstractRestrictionType getElementRestrictionType(String type){
        if(CommUtil.isNull(type) || !type.contains(".") || type.split("\\.").length < 2){
            return null;
        }else{
            String[] arr = type.split("\\.");
            //搜索限制类型
            return OdbFactory.searchRestrictionType(arr[0], arr[1]);
        }
    }
}
