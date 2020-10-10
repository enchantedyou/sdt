package com.ssy.api.factory.odb;

import com.ssy.api.entity.lang.TwoTuple;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.defaults.TableType;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;

/**
 * @Description odb工厂
 * @Author sunshaoyu
 * @Date 2020年06月12日-15:03
 */
@Slf4j
public class OdbFactory {

    /**
     * @Description 通过id搜索限制类型
     * @Author sunshaoyu
     * @Date 2020/6/13-1:26
     * @param id    限制类型id
     * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
     */
    public static AbstractRestrictionType searchRestrictionType(String id){
        return searchRestrictionType(null, id);
    }

    /**
     * @Description 通过限制类型的位置和id搜索限制类型
     * @Author sunshaoyu
     * @Date 2020/6/13-1:31
     * @param location  限制类型位置
     * @param id    限制类型id
     * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
     */
    public static AbstractRestrictionType searchRestrictionType(String location, String id){
        Map<String, Map<String, AbstractRestrictionType>> restrictionDataMap = MetaDataFactory.loadRestrictionTypeMap();
        Map<String, AbstractRestrictionType> restrictionTypeMap = null;

        if(CommUtil.isNull(location)){
            for(String local : restrictionDataMap.keySet()){
                restrictionTypeMap = restrictionDataMap.get(local);

                if(CommUtil.isNotNull(restrictionTypeMap)){
                    AbstractRestrictionType restrictionType = restrictionTypeMap.get(id);
                    //搜索到结果则返回
                    if(CommUtil.isNotNull(restrictionType)){
                        return restrictionType;
                    }
                }
            }
        }else{
            restrictionTypeMap = restrictionDataMap.get(location);
            return CommUtil.isNull(restrictionTypeMap) ? null : restrictionTypeMap.get(id);
        }
        return null;
    }

    /**
     * @Description 搜索普通的复合类型
     * @Author sunshaoyu
     * @Date 2020/6/13-17:58
     * @param complexTypeId
     * @return com.ssy.api.meta.defaults.ComplexType
     */
    public static ComplexType searchComplexType(String complexTypeId){
        return searchComplexType(null, complexTypeId);
    }

    /**
     * @Description 搜索普通的复合类型
     * @Author sunshaoyu
     * @Date 2020/6/13-17:55
     * @param location
     * @param complexTypeId
     * @return com.ssy.api.meta.defaults.ComplexType
     */
    public static ComplexType searchComplexType(String location, String complexTypeId){
        Map<String, Map<String, ComplexType>> complexTypeDataMap = MetaDataFactory.loadComplexTypeMap();
        Map<String, ComplexType> complexTypeMap = null;
        if(CommUtil.isNull(location)){
            for(String local : complexTypeDataMap.keySet()){
                complexTypeMap = complexTypeDataMap.get(local);

                if(CommUtil.isNotNull(complexTypeMap)){
                    ComplexType complexType = complexTypeMap.get(complexTypeId);
                    //如果元素id为空直接返回结果
                    if(CommUtil.isNotNull(complexType)){
                        return complexType;
                    }
                }
            }
        }else{
            complexTypeMap = complexTypeDataMap.get(location);
            return CommUtil.isNull(complexTypeMap) ? null : complexTypeMap.get(complexTypeId);
        }
        return null;
    }

    /**
     * @Description 字典搜索
     * @Author sunshaoyu
     * @Date 2020/6/13-17:59
     * @param dictId
     * @return com.ssy.api.meta.defaults.Element
     */
    public static Element searchDict(String dictId){
        Map<String, Element> dictDataMap = MetaDataFactory.loadDictMap();
        return dictDataMap.get(dictId);
    }

    /**
     * @Description 获取字典的map
     * @Author sunshaoyu
     * @Date 2020/7/16-13:42
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.Element>
     */
    public static Map<String, Element> getDictMap(){
        return MetaDataFactory.loadDictMap();
    }

    /**
     * @Description 搜索表模型
     * @Author sunshaoyu
     * @Date 2020/6/14-14:17
     * @param tableId
     * @return com.ssy.api.meta.defaults.TableType
     */
    public static TableType searchTable(String tableId){
        return MetaDataFactory.loadTableTypeMap().get(tableId);
    }

    /**
     * @Description 搜索文件
     * @Author sunshaoyu
     * @Date 2020/6/30-11:28
     * @param fileName
     * @return java.io.File
     */
    public static File searchFile(String fileName){
        return MetaDataFactory.loadProjectFileMap().get(fileName);
    }

    /**
     * @Description 获取动态数据源的map
     * @Author sunshaoyu
     * @Date 2020/7/3-15:01
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     */
    public static Map<Object, Object> getDataSourceMap(){
        return MetaDataFactory.loadDynamicDataSource();
    }

    /**
     * @Description 刷新动态数据源
     * @Author sunshaoyu
     * @Date 2020/7/3-16:02
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     */
    public static Map<Object, Object> refreshDynamicDataSource(){
        return MetaDataFactory.refreshDynamicDataSource();
    }

    /**
     * @Description 清除元数据缓存
     * @Author sunshaoyu
     * @Date 2020/9/27-14:42
     */
    public static void clear(){
        MetaDataFactory.clear();
    }

    /**
     * @Description 刷新元数据
     * @Author sunshaoyu
     * @Date 2020/7/23-13:17
     */
    public static void refreshMetaData(){
        MetaDataFactory.loadMetaDataInitially();
    }

    /**
     * @Description 元数据规范化处理
     * @Author sunshaoyu
     * @Date 2020/9/3-14:04
     * @param module
     */
    public static void metaDataNormalization(String module){
        MetaDataFactory.metaDataNormalization(module, null);
    }

    /**
     * @Description 元数据规范化处理
     * @Author sunshaoyu
     * @Date 2020/9/4-13:43
     * @param module
     * @param customReplaceMap  自定义替换参数(字典id, <字典引用ref, 字典类型type>)
     */
    public static void metaDataNormalization(String module, Map<String, TwoTuple<String, String>> customReplaceMap){
        MetaDataFactory.metaDataNormalization(module, customReplaceMap);
    }

    /**
     * @Description 初始化加载元数据
     * @Author sunshaoyu
     * @Date 2020/7/8-15:57
     */
    public static void loadMetaDataInitially() {
        MetaDataFactory.loadMetaDataInitially();
    }

    /**
     * @Description 加载动态数据源(优先从缓存中获取)
     * @Author sunshaoyu
     * @Date 2020/7/3-13:25
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     */
    public static Map<Object, Object> loadDynamicDataSource(){
        return MetaDataFactory.loadDynamicDataSource();
    }
}
