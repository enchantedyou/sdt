package com.ssy.api.factory.loader.impl;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.local.SdpEnumPriorty;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.factory.loader.RestrictionLoader;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.DefaultBaseType;
import com.ssy.api.meta.defaults.DefaultEnumType;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
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
@Slf4j
public class DefaultRestrictionLoader implements RestrictionLoader {
    @Autowired
    private SdtContextConfig sdtContextConfig;

    @Override
    public Map<String, Map<String, AbstractRestrictionType>> load(Map<String, File> fileMap) {
        Map<String, Map<String, AbstractRestrictionType>> map = new ConcurrentHashMap<>();

        for(Map.Entry<String, File> fileEntry : fileMap.entrySet()){
            String fileName = fileEntry.getKey();
            if(fileName.contains(SdtConst.ENUM_SUFFIX) || fileName.contains(SdtConst.REUSABLE_SUFFIX)){
                try {
                    Element rootNode = XmlParser.getXmlRootElement(fileMap.get(fileName));
                    String location = rootNode.attributeValue("id");
                    Map<String, AbstractRestrictionType> currentRestrictionTypeMap = CommUtil.nvl(map.get(location), new ConcurrentHashMap<>());

                    List<Element> restrictionTypeList = XmlParser.searchTargetAllXmlElement(rootNode, SdtConst.RESTRICTIONTYPE_NODE_NAME);
                    for(Element e : restrictionTypeList){
                        String restrictionTypeId = e.attributeValue("id");
                        List<Element> enumerationList = XmlParser.searchTargetAllXmlElement(e, SdtConst.ENUMERATION_NODE_NAME);
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
                        currentRestrictionTypeMap.put(restrictionTypeId, currentRestrictionType);
                    }
                    map.put(location, currentRestrictionTypeMap);
                } catch (Exception e) {
                    ApPubErr.E0007(e);
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
    public AbstractRestrictionType checkEnumPriorty(Map<String, SdpEnumPriorty> priority, AbstractRestrictionType before, AbstractRestrictionType now){
        //之前的数据为空或[当前或之前是微服务模型,但不是微服务模型优先],直接添加
        if(CommUtil.isNull(before)
                || ((SdtBusiUtil.isMsModel(now.getLocation()) || SdtBusiUtil.isMsModel(before.getLocation()))
                && !sdtContextConfig.getMsModelFirst())){
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
                    log.info("Restricted type [{}] has lower priority than [{}] and should be removed", now.getFullId(), before.getFullId());
                    return before;
                }else{
                    log.info("Restricted type [{}] has lower priority than [{}] and should be removed", before.getFullId(), now.getFullId());
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
    @Deprecated
    private AbstractRestrictionType searchBefore(Map<String, Map<String, AbstractRestrictionType>> map, String id){
        for(Map.Entry<String, Map<String, AbstractRestrictionType>> entrySet : map.entrySet()){
            AbstractRestrictionType e = entrySet.getValue().get(id);
            if(CommUtil.isNotNull(e)){
                return e;
            }
        }
        return null;
    }
}
