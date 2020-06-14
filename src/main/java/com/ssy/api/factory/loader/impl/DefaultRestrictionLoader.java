package com.ssy.api.factory.loader.impl;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.local.SdpEnumPriorty;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.factory.loader.RestrictionLoader;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.DefaultBaseType;
import com.ssy.api.meta.defaults.DefaultEnumType;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.servicetype.ModulePriortyService;
import com.ssy.api.utils.CommUtil;
import com.ssy.api.utils.XmlUtil;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 默认限制类型加载器
 * @Author sunshaoyu
 * @Date 2020/6/12-23:16
 */
@Component
public class DefaultRestrictionLoader implements RestrictionLoader {

    @Autowired
    private ModulePriortyService modulePriortyService;
    //log4j2日志
    private static final Logger logger = LoggerFactory.getLogger(DefaultRestrictionLoader.class);

    @Override
    public Map<String, Map<String, AbstractRestrictionType>> load(Map<String, File> fileMap) {
        Map<String, Map<String, AbstractRestrictionType>> map = new ConcurrentHashMap<>();
        Map<String, SdpEnumPriorty> priority = modulePriortyService.getEnumPriortyMap(false);

        for(String fileName : fileMap.keySet()){
            if(fileName.contains(SdtConst.ENUM_SUFFIX) || fileName.contains(SdtConst.REUSABLE_SUFFIX)){
                try {
                    Element rootNode = XmlUtil.getXmlRootElement(fileMap.get(fileName));
                    String location = rootNode.attributeValue("id");
                    Map<String, AbstractRestrictionType> currentRestrictionTypeMap = CommUtil.nvl(map.get(location), new ConcurrentHashMap<>());

                    List<Element> restrictionTypeList = XmlUtil.searchTargetAllXmlElement(rootNode, SdtConst.RESTRICTIONTYPE_NODE_NAME);
                    for(Element e : restrictionTypeList){
                        String restrictionTypeId = e.attributeValue("id");

                        List<Element> enumerationList = XmlUtil.searchTargetAllXmlElement(e, SdtConst.ENUMERATION_NODE_NAME);
                        AbstractRestrictionType currentRestrictionType = null;

                        Integer maxLength = CommUtil.isNull(e.attributeValue("maxLength")) ? SdtConst.DEFAULT_RESTRICTION_LENGTH : Integer.parseInt(e.attributeValue("maxLength"));
                        Integer fractionDigits = CommUtil.isNull(e.attributeValue("fractionDigits")) ? SdtConst.DEFAULT_RESTRICTION_DIGITS : Integer.parseInt(e.attributeValue("fractionDigits"));
                        //复用类型
                        if(CommUtil.isNull(enumerationList)){
                            currentRestrictionType = new DefaultBaseType(
                                    location, restrictionTypeId, e.attributeValue("longname"), e.attributeValue("base"), maxLength, fractionDigits
                            );
                        }
                        //枚举类型
                        else{
                            //加载枚举子元素列表
                            Map<String, DefaultEnumerationType> enumerationMap = new HashMap<>();
                            for(Element enumeration : enumerationList){
                                enumerationMap.put(enumeration.attributeValue("id"), new DefaultEnumerationType(
                                        location, enumeration.attributeValue("id"), enumeration.attributeValue("longname"), enumeration.attributeValue("value")
                                ));
                            }

                            currentRestrictionType = new DefaultEnumType(
                                    location, restrictionTypeId, e.attributeValue("longname"), e.attributeValue("base"), maxLength, enumerationMap
                            );
                        }

                        //优先级检查
                        AbstractRestrictionType before = searchBefore(map, restrictionTypeId);
                        AbstractRestrictionType suitableValue = checkEnumPriorty(priority, before, currentRestrictionType);
                        currentRestrictionTypeMap.put(restrictionTypeId, suitableValue);
                    }
                    map.put(location, currentRestrictionTypeMap);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to load restriction type model", e);
                }
            }
        }
        return map;
    }

    /**
     * @Description 枚举优先级检查
     * @Author sunshaoyu
     * @Date 2020/6/13-1:15
     * @param before
     * @param now
     * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
     */
    private AbstractRestrictionType checkEnumPriorty(Map<String, SdpEnumPriorty> priority, AbstractRestrictionType before, AbstractRestrictionType now){
        //之前的数据为空,直接添加
        if(CommUtil.isNull(before)){
            return now;
        }else{
            //获取两者的优先级
            SdpEnumPriorty beforeEnumPriorty = priority.get(before.getLocation());
            SdpEnumPriorty nowEnumPriorty = priority.get(now.getLocation());

            //检查元数据模型的优先级是否存在
            if(CommUtil.isNull(beforeEnumPriorty)){
                ApPubErr.E0008(before.getLocation());
            }else if(CommUtil.isNull(nowEnumPriorty)){
                ApPubErr.E0008(now.getLocation());
            }

            //两者都不含通配符且两者的组号不一致,不处理
            if(!beforeEnumPriorty.getGroupId().contains(SdtConst.WILDCARD) && !nowEnumPriorty.getGroupId().contains(SdtConst.WILDCARD) && !CommUtil.equals(beforeEnumPriorty.getGroupId(), nowEnumPriorty.getGroupId())) {
                return now;
            }
            //返回优先级较高的一方
            else{
                if(CommUtil.compare(beforeEnumPriorty.getEnumPriority(), nowEnumPriorty.getEnumPriority()) < 0){
                    logger.info("Restricted type [{}] has lower priority than [{}] and should be removed", nowEnumPriorty.getEnumType(), beforeEnumPriorty.getEnumType());
                    return before;
                }else{
                    return now;
                }
            }
        }
    }

    /**
     * @Description 从已存在的数据中搜索限制类型
     * @Author sunshaoyu
     * @Date 2020/6/13-21:18
     * @param map
     * @param id
     * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
     */
    private AbstractRestrictionType searchBefore(Map<String, Map<String, AbstractRestrictionType>> map, String id){
        for(String local : map.keySet()){
            AbstractRestrictionType e = map.get(local).get(id);
            if(CommUtil.isNotNull(e)){
                return e;
            }
        }
        return null;
    }
}
