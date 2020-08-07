package com.ssy.api.factory.loader;

import java.io.File;
import java.io.IOException;
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


    /**
     * @Description 加载文件内容并转化为字符串
     * @Author sunshaoyu
     * @Date 2020/6/22-21:03
     * @param file  文件
     * @param charset   字符集
     * @return java.lang.String
     */
    public String loadContentToString(File file, String charset) throws IOException;

    /**
     * @Description 文件保存
     * @Author sunshaoyu
     * @Date 2020/7/24-10:04
     * @param buffer    要保存的流
     * @param filePath  文件存储路径
     */
    public void saveFile(byte[] buffer , String filePath) throws IOException;

    /**
     * @Description 文件保存
     * @Author sunshaoyu
     * @Date 2020/7/30-13:36
     * @param str   要保存的字符串
     * @param filePath  文件存储路径
     */
    public void saveFile(String str , String filePath) throws IOException;
}