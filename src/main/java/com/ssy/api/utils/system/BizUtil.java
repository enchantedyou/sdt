package com.ssy.api.utils.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_DATECYCLE;
import com.ssy.api.entity.enums.E_STRGENTYPE;
import com.ssy.api.entity.lang.RunEnvs;
import com.ssy.api.entity.table.ap.ApbFieldNormal;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.exception.SdtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
     * @param throwable
     */
    public static void logError(Throwable throwable) {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;
        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter, true);
            throwable.printStackTrace(printWriter);
            log.error(stringWriter.toString());
        }finally {
            if(CommUtil.isNotNull(printWriter)) {
                printWriter.flush();
                printWriter.close();
            }

            if(CommUtil.isNotNull(stringWriter)) {
                stringWriter.flush();
                try{
                    stringWriter.close();
                }catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
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
     * @Description 获取当前日期
     * @Author sunshaoyu
     * @Date 2020/7/14-18:47
     * @return java.lang.String
     */
    public static String getCurSysDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * @Description yyyy-MM-dd HH:mm:ss.SSS
     * @Author sunshaoyu
     * @Date 2020/7/13-14:29
     * @param beginTime
     * @param endTime
     * @return java.lang.String
     */
    public static String calTimeConsume(String beginTime,String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try{
            Date begin = sdf.parse(beginTime);
            Date end = sdf.parse(endTime);
            return String.valueOf(end.getTime() - begin.getTime());
        }catch(Exception e){
            logError(e);
        }
        return null;
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
    public static int auditOnUpdate(Object oldData, Object newData) {
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
        log.info("{} finished,cost: {}ms", msg, s.getTotalTimeMillis());
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

    /**
     * @Description 对象克隆
     * @Author sunshaoyu
     * @Date 2020/6/17-11:44
     * @param clazz
     * @param obj
     * @return T
     */
    public static <T> T clone(Class<T> clazz, T obj){
        return JSONObject.parseObject(JSONObject.toJSONString(obj), clazz);
    }

    /**
     * @Description 日期相加减
     * @Author sunshaoyu
     * @Date 2020/7/2-14:55
     * @param dateType  日期类型
     * @param date  基准日期
     * @param num   基准数,为正表示日期增加,为负表示日期减少
     * @return java.lang.String
     */
    public static String dataAdd(E_DATECYCLE dateType, String date, int num){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            calendar.add(dateType.getValue(), num);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            throw new SdtException(e);
        }
    }

    /**
     * @Description 第一个日期减第二个日期,返回两者相差的天数
     * @Author sunshaoyu
     * @Date 2020/7/2-15:25
     * @param laterDate 较晚的日期
     * @param earlierDate   较早的日期
     * @return int
     */
    public static int dataDiff(String laterDate, String earlierDate){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(laterDate));
            long laterMillis = calendar.getTimeInMillis();

            calendar.setTime(simpleDateFormat.parse(earlierDate));
            long earlierMillis = calendar.getTimeInMillis();
            return (int) ((laterMillis - earlierMillis) / (1000L * 60L * 60L * 24L));
        } catch (ParseException e) {
            throw new SdtException(e);
        }
    }

    /**
     * @Description 检查日期字符串是否yyyyMMdd格式的日期
     * @Author sunshaoyu
     * @Date 2020/7/5-16:19
     * @param date
     * @return boolean
     */
    public static boolean isDateString(String date) {
        return isDateString(date, "yyyyMMdd");
    }

    /**
     * @Description 检查日期字符串是否指定格式的日期
     * @Author sunshaoyu
     * @Date 2020/7/5-16:19
     * @param date
     * @param format
     * @return boolean
     */
    public static boolean isDateString(String date, String format) {
        boolean bool = false;
        if(CommUtil.isNotNull(date)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                bool = CommUtil.equals(date, simpleDateFormat.format(parseToDate(date, format)));
            } catch (ParseException e) {
                BizUtil.logError(e);
            }
        }
        return bool;
    }

    /**
     * @Description 将日期字符串按指定格式转换为Date对象
     * @Author sunshaoyu
     * @Date 2020/7/5-16:13
     * @param date
     * @param format
     * @return java.util.Date
     */
    public static Date parseToDate(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    /**
     * @Description 系统暂停
     * @Author sunshaoyu
     * @Date 2020/7/5-18:17
     * @param mills
     */
    public static void systemPause(long mills) {
        try{
            Thread.sleep(mills);
        }catch(InterruptedException e) {
            logError(e);
        };
    }

    /**
     * @Description 初始化运行时环境
     * @Author sunshaoyu
     * @Date 2020/7/6-14:50
     */
    public static void initRunEnvs(){
        RunEnvs runEnvs = new RunEnvs(buildTrxnSeq(SdtConst.TRXN_SEQ_LENGTH), getCurSysTime());
        SpringContextUtil.getRequest().getSession().setAttribute(SdtConst.RUN_ENVS, runEnvs);
    }

    /**
     * @Description 获取运行时环境
     * @Author sunshaoyu
     * @Date 2020/7/6-14:51
     * @return com.ssy.api.entity.lang.RunEnvs
     */
    public static RunEnvs getRunEnvs(){
        RunEnvs runEnvs = (RunEnvs) SpringContextUtil.getRequest().getSession().getAttribute(SdtConst.RUN_ENVS);
        return CommUtil.nvl(runEnvs, new RunEnvs());
    }

    /**
     * @Description 分页
     * @Author sunshaoyu
     * @Date 2020/7/17-12:17
     * @param currentPage
     * @param pageSize
     * @param list
     * @return com.github.pagehelper.PageInfo<T>
     */
    public static <T> PageInfo<T> listToPage(int currentPage, int pageSize, List<T> list){
        RunEnvs runEnvs = getRunEnvs();
        currentPage = currentPage > 0 ? currentPage : runEnvs.getCurrentPage();
        pageSize = pageSize > 0 ? pageSize : runEnvs.getPageSize();
        int totalCount = list.size();

        //计算下标
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = CommUtil.getSmaller(startIndex + pageSize, totalCount);

        //开始分页
        Page<T> page = new Page<>(currentPage, pageSize);
        page.setTotal(totalCount);
        page.addAll(list.subList(startIndex, endIndex));

        if(CommUtil.isNotNull(runEnvs)){
            runEnvs.setTotalCount(totalCount);
        }
        return new PageInfo<>(page);
    }

    /**
     * @Description 分页
     * @Author sunshaoyu
     * @Date 2020/7/17-12:29
     * @param list
     * @return com.github.pagehelper.PageInfo<T>
     */
    public static <T> PageInfo<T> listToPage(List<T> list){
        return listToPage(0, 0, list);
    }

    /**
     * @Description 获取文件类型后缀(abc.serviceImpl.xml->xml)
     * @Author sunshaoyu
     * @Date 2020/7/29-15:37
     * @param name
     * @return java.lang.String
     */
    public static String getFileType(String name){
        return name.substring(name.lastIndexOf(".") + 1);
    }

    /**
     * @Description 获取文件名,不包含后缀(abc.serviceImpl.xml->abc.serviceImpl)
     * @Author sunshaoyu
     * @Date 2020/7/29-16:03
     * @param name
     * @return java.lang.String
     */
    public static String getFileName(String name){
        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * @Description 获取文件的真实名称(abc.serviceImpl.xml->abc)
     * @Author sunshaoyu
     * @Date 2020/8/5-13:05
     * @param name
     * @return java.lang.String
     */
    public static String getFileRealName(String name){
        return name.substring(0, name.indexOf("."));
    }
}
