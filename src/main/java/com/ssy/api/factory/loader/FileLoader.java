package com.ssy.api.factory.loader;

import java.io.File;
import java.util.Map;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年05月27日-16:32
 */
public interface FileLoader {

    /**
     * @Description 加载路径下的所有文件及子文件
     * @Author sunshaoyu
     * @Date 2020/5/28-13:55
     * @param path  目录路径
     * @param isLimitJavaReources 是否限制在java资源目录中加载
     * @param suffix    指定文件后缀
     * @return java.util.Map<java.lang.String,java.io.File>(文件名, 文件实体)
     */
    public Map<String, File> load(String path, boolean isLimitJavaReources, String... suffix);
}
