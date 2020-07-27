package com.ssy.api.utils.http;

import com.ssy.api.utils.system.CommUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月13日-14:39
 */
public class HttpServletUtil {

    /**
     * @Description 获取请求的ip地址
     * @Author sunshaoyu
     * @Date 2020/7/13-14:36
     * @param request
     * @return java.lang.String
     */
    public static String getRemoteHostAddr(HttpServletRequest request) {
        if (CommUtil.isNull(request.getHeader("x-forwarded-for"))) {
            return request.getRemoteAddr();
        }else{
            return request.getHeader("x-forwarded-for");
        }
    }

    /**
     * @Description 获取应用基础路径
     * @Author sunshaoyu
     * @Date 2020/7/24-9:56
     * @param request
     * @return java.lang.String
     */
    public static String getApplicationBasePath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * @Description 获取应用的指定路径
     * @Author sunshaoyu
     * @Date 2020/7/24-9:57
     * @param request
     * @param path
     * @return java.lang.String
     */
    public static String getApplicationTargetPath(HttpServletRequest request, String path){
        return request.getSession().getServletContext().getRealPath("/" + path);
    }

    /**
     * @Description 文件下载
     * @Author sunshaoyu
     * @Date 2020/7/24-11:39
     * @param file
     * @param response
     */
    public static void fileDownload(File file, HttpServletResponse response) throws IOException {
        BufferedInputStream bufferedInputStream = null;
        try{
            if(CommUtil.isNotNull(file) && file.exists()){
                //设置请求头
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
                byte[] buffer = new byte[(int) file.length()];

                //创建输入流并读取文件
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                OutputStream outputStream = response.getOutputStream();
                int len = -1;

                while((len = bufferedInputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0 ,len);
                }
            }
        }finally {
            if(bufferedInputStream != null){
                bufferedInputStream.close();
            }
        }
    }
}
