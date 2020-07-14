package com.ssy.api.factory.odb;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.factory.loader.LoaderFactory;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.defaults.TableType;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 元数据工厂,从加载器加载数据
 * @Author sunshaoyu
 * @Date 2020年06月13日-15:45
 */
@Component
public class MetaDataFactory {

    //加载器工厂
    private static LoaderFactory loaderFactory;
    //上下文配置
    private static SdtContextConfig sdtContextConfig;

    //元数据容器
    private static Map<String, File> projectFileMap = new ConcurrentHashMap<>();
    private static Map<String, File> intfExcelFileMap = new ConcurrentHashMap<>();
    private static Map<String, Map<String, AbstractRestrictionType>> restrictionTypeMap = new ConcurrentHashMap<>();
    private static Map<String, Map<String, ComplexType>> complexTypeMap = new ConcurrentHashMap<>();
    private static Map<String, Element> dictMap = new ConcurrentHashMap<>();
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

    /**
     * @Description 加载接口文档文件列表
     * @Author sunshaoyu
     * @Date 2020/6/13-16:07
     * @return java.util.Map<java.lang.String,java.io.File>
     */
    protected static Map<String, File> loadIntfWordFileMap(){
        if(CommUtil.isNull(intfExcelFileMap)){
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
     * @Description 加载字典
     * @Author sunshaoyu
     * @Date 2020/6/13-17:50
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.Element>
     */
    protected static Map<String, Element> loadDictMap(){
        if(CommUtil.isNull(dictMap)){
            StopWatch s = BizUtil.startStopWatch();
            Map<String, Map<String, ComplexType>> map = loadComplexTypeMap();

            for(String location : map.keySet()){
                Map<String, ComplexType> cMap = map.get(location);
                for(String key : cMap.keySet()){
                    ComplexType c = cMap.get(key);
                    if(c.isDict()){
                        dictMap.putAll(c.getElementMap());
                    }
                }
            }
            BizUtil.stoptStopWatch(s, "Load dict into containers");
        }
        return dictMap;
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
}
