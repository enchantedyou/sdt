package com.ssy.api.factory.loader.impl;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.utils.CommUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 默认文件加载器
 * @Author sunshaoyu
 * @Date 2020年05月28日-14:28
 */
@Component
public class DefaultFileLoader implements FileLoader {

    @Override
    public Map<String, File> load(String path, boolean isLimitJavaReources, String... suffix) {
       Map<String, File> map = new ConcurrentHashMap<>();
       try{
           File file = new File(path);
           loadFile(map, file, isLimitJavaReources, suffix);
       }catch(Exception e){
           throw new RuntimeException("An unexpected exception occurred while loading the file", e);
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
            //排除非java资源目录
            if(isLimitJavaReources && !file.getPath().contains(SdtConst.JAVA_RESOURCES_PATH)){
                return;
            }

            String fileName = file.getName();
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
}
