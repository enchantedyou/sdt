package com.ssy.api.factory.odb;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.entity.lang.TwoTuple;
import com.ssy.api.entity.table.local.SdpDictPriorty;
import com.ssy.api.entity.table.local.SdpEnumPriorty;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.LoaderFactory;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.defaults.TableType;
import com.ssy.api.servicetype.ModulePriortyService;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 元数据工厂,从加载器加载数据
 * @Author sunshaoyu
 * @Date 2020年06月13日-15:45
 */
@Component
@Slf4j
public class MetaDataFactory {

    //加载器工厂
    private static LoaderFactory loaderFactory;
    //上下文配置
    private static SdtContextConfig sdtContextConfig;
    //模块优先级服务
    private static ModulePriortyService modulePriortyService;

    //元数据容器
    private static Map<String, File> projectFileMap = new ConcurrentHashMap<>();
    private static Map<String, File> intfExcelFileMap = new ConcurrentHashMap<>();
    private static Map<String, Map<String, AbstractRestrictionType>> restrictionTypeMap = new ConcurrentHashMap<>();
    private static Map<String, Map<String, ComplexType>> complexTypeMap = new ConcurrentHashMap<>();
    private static Map<String, Element> dictMap = new ConcurrentHashMap<>();
    private static Map<String, AbstractRestrictionType> enumMap = new ConcurrentHashMap<>();
    private static Map<String, TableType> tableTypeMap = new ConcurrentHashMap<>();
    private static Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();

    @Autowired
    public void setLoaderFactory(LoaderFactory loaderFactory) {
        MetaDataFactory.loaderFactory = loaderFactory;
    }

    @Autowired
    public void setSdtContextConfig(SdtContextConfig sdtContextConfig) {
        MetaDataFactory.sdtContextConfig = sdtContextConfig;
    }

    @Autowired
    public void setModulePriortyService(ModulePriortyService modulePriortyService) {
        MetaDataFactory.modulePriortyService = modulePriortyService;
    }

    /**
     * @Description 加载接口文档文件列表
     * @Author sunshaoyu
     * @Date 2020/6/13-16:07
     * @return java.util.Map<java.lang.String,java.io.File>
     */
    protected static Map<String, File> loadIntfWordFileMap(){
        if(CommUtil.isNull(intfExcelFileMap) && CommUtil.isNotNull(sdtContextConfig.getIntfExcelPath())){
            StopWatch s = BizUtil.startStopWatch();
            intfExcelFileMap = loaderFactory.getFileLoader().load(sdtContextConfig.getIntfExcelPath(), false, SdtConst.INTF_EXCEL_SUFFIX);
            BizUtil.stoptStopWatch(s, "Load interface document file list");
        }
        return intfExcelFileMap;
    }

    /**
     * @Description 加载项目文件列表
     * @Author sunshaoyu
     * @Date 2020/6/13-16:15
     * @return java.util.Map<java.lang.String,java.io.File>
     */
    protected static Map<String, File> loadProjectFileMap(){
        if(CommUtil.isNull(projectFileMap)){
            StopWatch s = BizUtil.startStopWatch();
            projectFileMap = loaderFactory.getFileLoader().load(sdtContextConfig.getWorkSpacePath(), true, SdtConst.PROJECT_FILE_SUFFIX);
            BizUtil.stoptStopWatch(s, "Load project file list");
        }
        return projectFileMap;
    }

    /**
     * @Description 加载限制类型
     * @Author sunshaoyu
     * @Date 2020/6/13-17:38
     * @return java.util.Map<java.lang.String,java.util.Map<java.lang.String,com.ssy.api.meta.abstracts.AbstractRestrictionType>>
     */
    protected static Map<String, Map<String, AbstractRestrictionType>> loadRestrictionTypeMap(){
        if(CommUtil.isNull(restrictionTypeMap)){
            Map<String, File> map = loadProjectFileMap();
            StopWatch s = BizUtil.startStopWatch();
            restrictionTypeMap = loaderFactory.getRestrictionLoader().load(map);
            BizUtil.stoptStopWatch(s, "Load restricted types into containers");
        }
        return restrictionTypeMap;
    }

    /**
     * @Description 加载复合类型
     * @Author sunshaoyu
     * @Date 2020/6/13-17:40
     * @return java.util.Map<java.lang.String,java.util.Map<java.lang.String,com.ssy.api.meta.defaults.ComplexType>>
     */
    protected static Map<String, Map<String, ComplexType>> loadComplexTypeMap(){
        if(CommUtil.isNull(complexTypeMap)){
            StopWatch s = BizUtil.startStopWatch();
            Map<String, File> map = loadProjectFileMap();
            complexTypeMap = loaderFactory.getComplexTypeLoader().load(map);
            BizUtil.stoptStopWatch(s, "Load complex types into containers");
        }
        return complexTypeMap;
    }

    /**
     * @Description 加载字典（取最高优先级）
     * @Author sunshaoyu
     * @Date 2020/6/13-17:50
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.Element>
     */
    protected static Map<String, Element> loadDictMap(){
        if(CommUtil.isNull(dictMap)){
            StopWatch s = BizUtil.startStopWatch();
            Map<String, SdpDictPriorty> dictPriortyMap = modulePriortyService.getDictPriortyMap();
            Map<String, Map<String, ComplexType>> map = loadComplexTypeMap();

            for(Map.Entry<String, Map<String, ComplexType>> entry : map.entrySet()){
                Map<String, ComplexType> cMap = entry.getValue();
                for(Map.Entry<String, ComplexType> subEntry : cMap.entrySet()){
                    ComplexType c = subEntry.getValue();
                    if(c.isDict()){
                        c.getElementMap().forEach((id, now) -> {
                            Element before = dictMap.get(id);
                            if(null == before){
                                dictMap.put(id, now);
                            }else{
                                dictMap.put(id, loaderFactory.getComplexTypeLoader().checkDictPriorty(dictPriortyMap, before, now));
                            }
                        });
                    }
                }
            }
            BizUtil.stoptStopWatch(s, "Load dict into containers");
        }
        return dictMap;
    }

    /**
     * @Description 加载枚举（取最高优先级）
     * @Author sunshaoyu
     * @Date 2020/9/7-10:06
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.abstracts.AbstractRestrictionType>
     */
    protected static Map<String, AbstractRestrictionType> loadEnumMap(){
        if(CommUtil.isNull(enumMap)){
            StopWatch s = BizUtil.startStopWatch();
            Map<String, SdpEnumPriorty> enumPriortyMap = modulePriortyService.getEnumPriortyMap();
            Map<String, Map<String, AbstractRestrictionType>> map = loadRestrictionTypeMap();

            map.forEach((location, eMap) -> {
                eMap.forEach((key, e) -> {
                    if(e.getRestriction() == E_RESTRICTION.ENUMTYPE){
                        AbstractRestrictionType before = enumMap.get(key);
                        if(null == before){
                            enumMap.put(key, e);
                        }else{
                            enumMap.put(key, loaderFactory.getRestrictionLoader().checkEnumPriorty(enumPriortyMap, before, e));
                        }
                    }
                });
            });
            BizUtil.stoptStopWatch(s, "Load enum into containers");
        }
        return enumMap;
    }

    /**
     * @Description 加载表模型
     * @Author sunshaoyu
     * @Date 2020/6/14-14:11
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.TableType>
     */
    protected static Map<String, TableType> loadTableTypeMap(){
        if(CommUtil.isNull(tableTypeMap)){
            StopWatch s = BizUtil.startStopWatch();
            Map<String, File> map = loadProjectFileMap();
            tableTypeMap = loaderFactory.getTableTypeLoader().load(map);
            BizUtil.stoptStopWatch(s, "Load table model into containers");
        }
        return tableTypeMap;
    }

    /**
     * @Description 刷新动态数据源(每次必定取最新的数据源)
     * @Author sunshaoyu
     * @Date 2020/7/3-13:23
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     */
    protected static Map<Object, Object> refreshDynamicDataSource(){
        StopWatch s = BizUtil.startStopWatch();
        dataSourceMap = loaderFactory.getDataSourceLoader().initDynamicDataSource();
        BizUtil.stoptStopWatch(s, "Refresh the dynamic data source");
        return dataSourceMap;
    }

    /**
     * @Description 加载动态数据源(优先从缓存中获取)
     * @Author sunshaoyu
     * @Date 2020/7/3-13:25
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     */
    protected static Map<Object, Object> loadDynamicDataSource(){
        if(CommUtil.isNull(dataSourceMap)){
            StopWatch s = BizUtil.startStopWatch();
            dataSourceMap = loaderFactory.getDataSourceLoader().initDynamicDataSource();
            BizUtil.stoptStopWatch(s, "Load the dynamic data source");
        }
        return dataSourceMap;
    }

    /**
     * @Description 初始化加载元数据
     * @Author sunshaoyu
     * @Date 2020/7/8-15:57
     */
    protected static void loadMetaDataInitially() {
        //初始化接口文档文件
        loadIntfWordFileMap();
        //初始化限制类型
        loadRestrictionTypeMap();
        //初始化复合类型
        loadComplexTypeMap();

        //初始化项目字典
        loadDictMap();
        //初始化项目枚举
        loadEnumMap();
        //初始化表模型
        loadTableTypeMap();
    }

    /**
     * @Description 清除元数据缓存
     * @Author sunshaoyu
     * @Date 2020/9/27-14:42
     */
    protected static void clear(){
        intfExcelFileMap.clear();
        restrictionTypeMap.clear();
        complexTypeMap.clear();
        dictMap.clear();
        enumMap.clear();
        tableTypeMap.clear();
    }

    /**
     * @Description 刷新元数据
     * @Author sunshaoyu
     * @Date 2020/8/24-13:53
     */
    protected static void refreshMetaData(){
        StopWatch s = BizUtil.startStopWatch();
        clear();
        loadMetaDataInitially();
        BizUtil.stoptStopWatch(s, "Refresh the meta data");
    }

    /**
     * @Description 元数据规范化处理
     * @Author sunshaoyu
     * @Date 2020/9/4-13:43
     * @param module    模块名称
     * @param customReplaceMap  自定义替换参数(字典id, <字典引用ref, 字典类型type>)
     */
    protected static void metaDataNormalization(String module, Map<String, TwoTuple<String, String>> customReplaceMap){
        Map<String, SdpDictPriorty> dictPriortyMap = modulePriortyService.getDictPriortyMap();
        loadProjectFileMap().forEach((fileName, file) -> {
            //非指定模块,不处理
            if(CommUtil.isNotNull(module) && !fileName.toLowerCase().substring(0, 7).contains(module)){
                return;
            }

            try{
                Map<String, String> replaceMap = new HashMap<>();
                /** 复合类型 **/
                if(fileName.contains(SdtConst.COMPLEX_SUFFIX)){
                    metaDataVerify(file, replaceMap, "element", dictPriortyMap, customReplaceMap);
                }
                /** flowtran、数据库表、报表、服务 **/
                else if(fileName.contains(SdtConst.FLOWTRAN_SUFFIX) || fileName.contains(SdtConst.TABLE_SUFFIX)
                        || fileName.contains(SdtConst.REPORT_SUFFIX) || fileName.contains(SdtConst.SERVICETYPE_SUFFIX)){
                    metaDataVerify(file, replaceMap, "field", dictPriortyMap, customReplaceMap);
                }
                /** 命名SQL **/
                else if(fileName.contains(SdtConst.NAMEDSQL_SUFFIX)){
                    metaDataVerify(file, replaceMap, "parameter", dictPriortyMap, customReplaceMap);
                }

                //文件内容替换并写入
                if(CommUtil.isNotNull(replaceMap)){
                    replaceStrByMap(file, replaceMap);
                }
            }catch (Exception e){
                throw new SdtException("Metadata normalization processing failed", e);
            }
        });
    }

    /**
     * @Description 元数据规范性校验
     * @Author sunshaoyu
     * @Date 2020/9/3-13:58
     * @param file
     * @param replaceMap
     */
    private static void metaDataVerify(File file, Map<String, String> replaceMap, String elementName, Map<String, SdpDictPriorty> dictPriortyMap, Map<String, TwoTuple<String, String>> customReplaceMap) throws DocumentException {
        Document document = XmlParser.getXmlDocument(file);
        XmlParser.searchTargetAllXmlElement(document.getRootElement(), elementName).forEach(e -> {
            String idAttrName = file.getName().contains(SdtConst.NAMEDSQL_SUFFIX) ? "property" : "id";
            Element dict = dictMap.get(e.attributeValue(idAttrName));
            org.dom4j.Element before = e.createCopy();
            TwoTuple<String, String> replaceTwoTuple = null;
            //获取自定义的替换值
            if(CommUtil.isNotNull(customReplaceMap)){
                replaceTwoTuple = customReplaceMap.get(e.attributeValue(idAttrName));
            }

            if(CommUtil.isNotNull(dict)){
                SdpDictPriorty dictPriorty = dictPriortyMap.get(dict.getLocation());
                //原字典优先级不存在,不处理
                if(CommUtil.isNull(replaceTwoTuple) && CommUtil.isNull(dictPriorty)){
                    return;
                }
                //不同组则不处理
                String groupId = dictPriorty.getGroupId();
                if(CommUtil.isNull(replaceTwoTuple) && !CommUtil.equals(file.getName().toLowerCase().substring(0, 2), groupId) && !CommUtil.equals(groupId, SdtConst.WILDCARD)){
                    return;
                }

                //引用
                Attribute refAttribute = e.attribute("ref");
                if(CommUtil.isNotNull(refAttribute) && CommUtil.isNotNull(refAttribute.getValue())){
                    String beforeValue = refAttribute.getValue();
                    if(!CommUtil.equals(dict.getRef(), refAttribute.getValue()) && CommUtil.isNull(replaceTwoTuple)){
                        refAttribute.setValue(dict.getRef());
                    }else if(CommUtil.isNotNull(replaceTwoTuple)){
                        refAttribute.setValue(CommUtil.nvl(replaceTwoTuple.getFirst(), refAttribute.getValue()));
                    }

                    if(!CommUtil.equals(beforeValue, refAttribute.getValue())){
                        log.info("[{}]The reference of element [{}] is updated {}->{}", file.getName(), dict.getId(), beforeValue, refAttribute.getValue());
                    }
                }
                //类型
                Attribute typeAttribute = file.getName().contains(SdtConst.NAMEDSQL_SUFFIX) ? e.attribute("javaType") : e.attribute("type");
                if(CommUtil.isNotNull(dict.getType()) && CommUtil.isNotNull(typeAttribute) && CommUtil.isNotNull(typeAttribute.getValue())){
                    String beforeValue = typeAttribute.getValue();
                    //多级引用,不处理
                    if(beforeValue.split("\\.").length > 2){
                        return;
                    }

                    if(!CommUtil.equals(typeAttribute.getValue(), dict.getType().getFullId()) && CommUtil.isNull(replaceTwoTuple)){
                        typeAttribute.setValue(dict.getType().getFullId());
                    }else if(CommUtil.isNotNull(replaceTwoTuple)){
                        typeAttribute.setValue(CommUtil.nvl(replaceTwoTuple.getSecond(), typeAttribute.getValue()));
                    }

                    if(!CommUtil.equals(beforeValue, typeAttribute.getValue())){
                        log.info("[{}]The type of element [{}] is updated {}->{}", file.getName(), dict.getId(), beforeValue, typeAttribute.getValue());
                    }
                }

                if(!CommUtil.equals(before.asXML(), e.asXML())){
                    replaceMap.put(before.asXML(), e.asXML());
                }
            }
        });
    }

    /**
     * @Description 根据map替换文件内容
     * @Author sunshaoyu
     * @Date 2020/9/3-13:54
     * @param file
     * @param map
     */
    private static void replaceStrByMap(File file, Map<String, String> map) throws IOException {
        String content = loaderFactory.getFileLoader().loadAsString(file, SdtConst.DEFAULT_ENCODING);
        for(String before : map.keySet()){
            content = content.replaceAll(before, map.get(before));
        }
        //loaderFactory.getFileLoader().saveFile(content, file.getPath());
    }
}
