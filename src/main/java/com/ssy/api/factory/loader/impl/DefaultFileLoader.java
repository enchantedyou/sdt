package com.ssy.api.factory.loader.impl;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 默认文件加载器
 * @Author sunshaoyu
 * @Date 2020年05月28日-14:28
 */
@Component
@Slf4j
public class DefaultFileLoader implements FileLoader {

    @Autowired
    private SdtContextConfig sdtContextConfig;

    @Override
    public Map<String, File> load(String path, boolean isLimitJavaReources, String... suffix) {
       Map<String, File> map = new ConcurrentHashMap<>();
       try{
           File file = new File(path);
           loadFile(map, file, isLimitJavaReources, suffix);
       }catch(Exception e){
           throw new SdtException("An unexpected exception occurred while loading the file", e);
       }
       return map;
    }

    /**
     * @Description 递归加载文件
     * @Author sunshaoyu
     * @Date 2020/6/13-19:49
     * @param map
     * @param file
     * @param isLimitJavaReources
     * @param suffix
     */
    private void loadFile(Map<String, File> map, File file, boolean isLimitJavaReources, String... suffix){
        if(file.isDirectory()){
            File[] subFileList = file.listFiles();
            for(File subFile : subFileList){
                loadFile(map, subFile, isLimitJavaReources, suffix);
            }
        }else{
            String fileName = file.getName();
            //排除非java资源目录
            if(isLimitJavaReources && !file.getPath().contains(SdtConst.JAVA_RESOURCES_PATH)){
                return;
            }else if(BizUtil.isRegexMatches(SdtConst.MS_MODEL_REG, fileName) && !sdtContextConfig.getMsModelFirst()){
                //当前是微服务模型但是非微服务模型优先,不加载
                log.info("The current file is [{}] but the microservice model priority indicator is [{}], so it is not loaded", fileName, sdtContextConfig.getMsModelFirst());
                return;
            }

            if(CommUtil.isNotNull(suffix)){
                if(fileName.contains(".")){
                    String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
                    for(String s : suffix){
                        if(s.equals(fileSuffix)){
                            map.put(fileName, file);
                        }
                    }
                }
            }else{
                map.put(fileName, file);
            }
        }
    }


    public String loadContentToString(File file, String charset) throws IOException {
        FileInputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            if(inputStream.read(buffer) > 0){
                return new String(buffer, charset);
            }
        } finally {
            //关闭流
            if(inputStream != null){
                inputStream.close();
            }
        }
        return null;
    }
}
