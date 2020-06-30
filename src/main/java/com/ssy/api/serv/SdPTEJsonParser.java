package com.ssy.api.serv;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.sump.component.PTEComponent;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.utils.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description PTE json   解析器
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:40
 */
@Component
@Slf4j
public class SdPTEJsonParser {

    private static SdtContextConfig sdtContextConfig;
    private static FileLoader fileLoader;

    @Autowired
    public void setSdtContextConfig(SdtContextConfig sdtContextConfig) {
        SdPTEJsonParser.sdtContextConfig = sdtContextConfig;
    }

    @Autowired
    public void setFileLoader(FileLoader fileLoader) {
        SdPTEJsonParser.fileLoader = fileLoader;
    }

    /** json对象map **/
    private static final Map<String, PTEComponent> pteComponentMap = new ConcurrentHashMap<>();

    /**
     * @Description 根据模板解析PTE json
     * @Author sunshaoyu
     * @Date 2020/6/22-20:47
     */
    public static void parsePTEJsonByModule() throws IOException {
        //加载所有的json文件
        Map<String, File> fileMap = fileLoader.load(sdtContextConfig.getSumpResourcePath(), false, ".json");

        for(String fileName : fileMap.keySet()){
            String jsonStr = fileLoader.loadContentToString(fileMap.get(fileName), SdtConst.DEFAULT_ENCODING);
            //System.out.println(fileMap.get(fileName));

            if(JSONObject.isValidObject(jsonStr)){
                try{
                    PTEComponent pteComponent = JSONObject.parseObject(jsonStr, PTEComponent.class, Feature.OrderedField);
                    pteComponentMap.put(fileName, pteComponent);
                }catch (JSONException e){
                    log.warn("Deserialization of json file {} failed", fileName);
                }
            }
        }
    }

    /**
     * @Description 检查PTE json是否初始化
     * @Author sunshaoyu
     * @Date 2020/6/23-14:05
     */
    private static void checkPTEInitialize(){
        try {
            if(CommUtil.isNull(pteComponentMap)){
                parsePTEJsonByModule();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize PTE json file");
        }
    }

    /**
     * @Description 搜索PTE组件
     * @Author sunshaoyu
     * @Date 2020/6/22-21:24
     * @param jsonName  json文件名
     * @return com.ssy.api.entity.sump.component.PTEComponent
     */
    public static PTEComponent searchOne(String jsonName) throws IOException {
        checkPTEInitialize();
        return pteComponentMap.get(jsonName);
    }
}
