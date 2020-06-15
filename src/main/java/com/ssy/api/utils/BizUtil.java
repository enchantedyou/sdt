package com.ssy.api.utils;

import com.ssy.api.entity.enums.E_STRGENTYPE;
import com.ssy.api.entity.table.comm.ApbFieldNormal;
import com.ssy.api.exception.ApPubErr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description Sunline developer tools工具类
 * @Author sunshaoyu
 * @Date 2020年06月11日-16:48
 */
@Slf4j
public class BizUtil {

    /**
     * @Description 必输校验
     * @Author sunshaoyu
     * @Date 2020/6/11-18:01
     * @param obj   待判断的对象
     * @param fieldName 对象名
     * @param fieldDesc 对象描述
     * @return void
     */
    public static void fieldNotNull(Object obj, String fieldName, String fieldDesc){
        if(CommUtil.isNull(obj)){
            ApPubErr.E0001(fieldName, fieldDesc);
        }
    }

    /**
     * @Description 字段不允许输入校验
     * @Author sunshaoyu
     * @Date 2020/6/11-18:03
     * @param obj   待判断的对象
     * @param fieldDesc 对象描述
     * @return void
     */
    public static void fieldNotAllowInput(Object obj, String fieldDesc){
        if(CommUtil.isNotNull(obj)){
            ApPubErr.E0002(fieldDesc);
        }
    }

    /**
     * @Description 日志输出详细错误信息
     * @Author sunshaoyu
     * @Date 2020/6/11-15:48
     * @param e
     */
    public static void logError(Exception e) throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        e.printStackTrace(printWriter);
        log.error(stringWriter.toString());

        stringWriter.flush();
        printWriter.flush();
        stringWriter.close();
        printWriter.close();
    }

    /**
     * @Description 获取当前系统时间,格式为yyyy-MM-dd HH:mm:ss.SSS
     * @Author sunshaoyu
     * @Date 2020/6/11-14:17
     * @return java.lang.String
     */
    public static String getCurSysTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(new Date());
    }

    /**
     * @Description 构建交易流水
     * @Author sunshaoyu
     * @Date 2020/6/11-14:18
     * @param len   流水长度
     * @return java.lang.String
     */
    public synchronized static String buildTrxnSeq(int len){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        len = len - 17 >= 0 ? len - 17 : 0;
        return sdf.format(new Date()) + CommUtil.randStr(len, E_STRGENTYPE.NUMBER);
    }

    /**
     * @Description 审计两个对象获取它们的数据变更数量
     * @Author sunshaoyu
     * @Date 2020/6/12-15:12
     * @param oldData
     * @param newData
     * @return int
     */
    public static int auditOnUpdate(Object oldData,Object newData) {
        if(null == oldData || null == newData){
            return 0;
        }else if(!oldData.getClass().equals(newData.getClass())){
            ApPubErr.E0003(oldData.getClass().getSimpleName(),newData.getClass().getSimpleName());
        }

        int updateNum = 0;
        Set<String> commFieldSet = getCommField();
        Map<String, Object> oldMap = CommUtil.toMap(oldData);
        Map<String, Object> newMap = CommUtil.toMap(newData);

        for(String key : oldMap.keySet()){
            Object o1 = oldMap.get(key);
            Object o2 = newMap.get(key);
            String beforeData = CommUtil.isNull(o1) ? "" : o1.toString();
            String afterData = CommUtil.isNull(o2) ? "" : o2.toString();

            if(CommUtil.equals(beforeData, afterData)){
                updateNum++;
            }
        }
        return updateNum;
    }

    /**
     * @Description 获取表公共字段集
     * @Author sunshaoyu
     * @Date 2020/6/12-14:45
     * @return java.util.Set<java.lang.String>
     */
    private static Set<String> getCommField(){
        Map<String, Object> map = CommUtil.toMap(new ApbFieldNormal());
        Set<String> set = new HashSet<>();
        for(String key : map.keySet()){
            set.add(key);
        }
        return set;
    }

    /**
     * @Description 开始计时
     * @Author sunshaoyu
     * @Date 2020/6/13-21:00
     * @return org.springframework.util.StopWatch
     */
    public static StopWatch startStopWatch(){
        StopWatch s = new StopWatch();
        s.start();
        return s;
    }

    /**
     * @Description 结束计时
     * @Author sunshaoyu
     * @Date 2020/6/13-21:01
     * @param s
     * @param msg
     */
    public static void stoptStopWatch(StopWatch s, String msg){
        s.stop();
        log.info("{} finished,cost:[{}ms]", msg, s.getTotalTimeMillis());
    }

    /**
     * @Description 检查正则表达式是否匹配
     * @Author sunshaoyu
     * @Date 2020/6/13-21:34
     * @param regex
     * @param input
     * @return boolean
     */
    public static boolean isRegexMatches(String regex,String input){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
