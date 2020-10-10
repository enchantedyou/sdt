package com.ssy.api.utils.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.entity.enums.E_STRGENTYPE;
import com.ssy.api.exception.SdtException;
import com.ssy.api.servicetype.Callback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description 公共工具类
 * @Author sunshaoyu
 * @Date 2020年06月11日-14:06
 */
@Slf4j
public class CommUtil {

    /**
     * @Description 判断对象是否为null或空值
     * @Author sunshaoyu
     * @Date 2020/6/11-14:08
     * @param obj
     * @return boolean
     */
    public static boolean isNull(Object obj) {
        if (null == obj){
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).trim().length() == 0 || obj.equals("null");
        } else if (obj instanceof Map) {
            return Map.class.cast(obj).isEmpty();
        } else if (obj instanceof Collection<?>) {
            return Collection.class.cast(obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        return false;
    }

    /**
     * @Description 如果value为空,取默认值,否则返回value
     * @Author sunshaoyu
     * @Date 2020/6/11-14:09
     * @param value
     * @param defaultValue
     * @return T
     */
    public static <T> T nvl(T value,T defaultValue){
        return isNull(value) ? defaultValue : value;
    }

    /**
     * @Description 判断对象是否不为null且不为空值
     * @Author sunshaoyu
     * @Date 2020/6/11-14:09
     * @param obj
     * @return boolean
     */
    public static boolean isNotNull(Object obj) {
        return !(isNull(obj));
    }

    /**
     * @Description 生成随机字符串
     * @Author sunshaoyu
     * @Date 2020/6/11-14:10
     * @param len   生成字符串的长度
     * @param model 生成模式: 纯数字; 纯大写字母; 纯小写字母; 任意字符串
     * @return java.lang.String
     */
    public static String randStr(int len, E_STRGENTYPE model) {
        Random rand = new Random();
        StringBuffer buffer = new StringBuffer();
        String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int start = -1, end = -1;

        switch (model) {
            case NUMBER:
                start = 52;
                end = 61;
                break;
            case UPPER:
                start = 26;
                end = 52;
                break;
            case LOWER:
                start = 0;
                end = 26;
                break;
            case ANY:
                start = 0;
                end = 61;
                break;
        }

        for (int i = 0; i < len; i++) {
            int index = rand.nextInt(end - start) + start;
            buffer.append(dict.charAt(index));
        }
        return buffer.toString();
    }

    /**
     * @Description 生成随机字符串
     * @Author sunshaoyu
     * @Date 2020/6/11-14:14
     * @param len   字符串长度
     * @return java.lang.String
     */
    public static String randStr(int len) {
        return randStr(len, E_STRGENTYPE.ANY);
    }

    /**
     * @Description 判断字符串是否包含中文
     * @Author sunshaoyu
     * @Date 2020/6/11-14:16
     * @param str
     * @return boolean
     */
    public static boolean isContainChinese(String str) {
        if (isNull(str)) {
            return false;
        }
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 0x4E00 && c[i] <= 0x29FA5) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description 比较两个值的大小
     * @Author sunshaoyu
     * @Date 2020/6/11-14:24
     * @param o1
     * @param o2
     * @return int
     */
    public static <T extends Comparable<? super T>> int compare(T o1, T o2){
        return compare(o1, o2, false, true);
    }

    /**
     * @Description 判断两个对象是否相等
     * @Author sunshaoyu
     * @Date 2020/6/12-14:48
     * @param o1
     * @param o2
     * @return boolean
     */
    public static <T extends Comparable<? super T>> boolean equals(T o1, T o2){
        return compare(o1, o2, false, true) == 0;
    }

    /**
     * @Description 比较两个值的大小
     * @Author sunshaoyu
     * @Date 2020/6/11-14:23
     * @param o1
     * @param o2
     * @param ignoreCase
     * @param ignoreNullAndEmpty
     * @return int
     */
    private static <T extends Comparable<? super T>> int compare(T o1, T o2, boolean ignoreCase, boolean ignoreNullAndEmpty) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            if ((ignoreNullAndEmpty) && (String.class.isAssignableFrom(o2.getClass())) && ("".equals(o2))) {
                return 0;
            }
            return -1;
        }
        if (o2 == null) {
            if ((ignoreNullAndEmpty) && (String.class.isAssignableFrom(o1.getClass())) && ("".equals(o1))) {
                return 0;
            }
            return 1;
        }
        if ((ignoreCase) && (String.class.isAssignableFrom(o1.getClass()))
                && (String.class.isAssignableFrom(o2.getClass()))) {
            return ((String) o1).compareToIgnoreCase((String) o2);
        }

        if ((o1 != null) && (o1.getClass().isEnum())) {
            o1 = (T) String.valueOf(o1);
        }

        if ((o2 != null) && (o2.getClass().isEnum())) {
            o2 = (T) String.valueOf(o2);
        }

        return o1.compareTo(o2);
    }

    /**
     * @Description 判断两个泛型是否相等
     * @Author sunshaoyu
     * @Date 2020/9/22-9:46
     * @param o1
     * @param o2
     * @return boolean
     */
    public static <T> boolean isGenericsEqual(T o1, T o2) {
        if((CommUtil.isNull(o1) && CommUtil.isNotNull(o2)) || (CommUtil.isNull(o2) && CommUtil.isNotNull(o1))) {
            //两者一个为空一个不为空,不相等
            return false;
        }else if(CommUtil.isNull(o1) && CommUtil.isNull(o2)) {
            //两者都为空,相等
            return true;
        }else if(o1 instanceof BigDecimal) {
            //BigDecimal判断
            return CommUtil.compare(BigDecimal.class.cast(o1), BigDecimal.class.cast(o2)) == 0;
        }else if(o1 instanceof String) {
            //String判断
            return CommUtil.compare(String.valueOf(o1), String.valueOf(o2)) == 0;
        }else if(o1 instanceof Long) {
            //Long判断
            return CommUtil.compare(Long.class.cast(o1), Long.class.cast(o2)) == 0;
        }
        return o1 == o2;
    }

    /**
     * @Description 将对象转换为map
     * @Author sunshaoyu
     * @Date 2020/6/12-14:41
     * @param obj
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> toMap(Object obj){
        Map<String, Object> map = new HashMap<>();
        if(isNotNull(obj)){
            Field[] fieldArray = obj.getClass().getDeclaredFields();
            for(Field f : fieldArray){
                try{
                    map.put(f.getName(), obj.getClass().getMethod(buildGetterMethodName(f.getName())));
                }catch (NoSuchMethodException e){
                    throw new SdtException(e);
                }
            }
        }
        return map;
    }

    /**
     * @Description 构建getter方法名
     * @Author sunshaoyu
     * @Date 2020/6/12-14:38
     * @param fieldName 字段名
     * @return java.lang.String
     */
    public static String buildGetterMethodName(String fieldName){
        StringBuffer buffer = new StringBuffer();
        return buffer.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
    }

    /**
     * @Description 构建setter方法名
     * @Author sunshaoyu
     * @Date 2020/7/2-17:23
     * @param fieldName
     * @return java.lang.String
     */
    public static String buildSetterMethodName(String fieldName){
        StringBuffer buffer = new StringBuffer();
        return buffer.append("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
    }

    /**
     * @Description 获取两个对象更小的一方
     * @Author sunshaoyu
     * @Date 2020/6/13-0:39
     * @param o1
     * @param o2
     * @return T
     */
    public static <T extends Comparable> T getSmaller(T o1, T o2){
        return compare(o1, o2) <= 0 ? o1 : o2;
    }

    /**
     * @Description 获取两个对象更大的一方
     * @Author sunshaoyu
     * @Date 2020/6/13-0:40
     * @param o1
     * @param o2
     * @return T
     */
    public static <T extends Comparable> T getBigger(T o1, T o2){
        return compare(o1, o2) >= 0 ? o1 : o2;
    }

    /**
     * @Description 数组转list
     * @Author sunshaoyu
     * @Date 2020/7/3-13:00
     * @param a
     * @return java.util.List<T>
     */
    public static <T> List<T> asList(T... a){
        List<T> list = new ArrayList<>();
        for(T t : a){
            list.add(t);
        }
        return list;
    }

    /**
     * @Description 对象间属性拷贝
     * @Author sunshaoyu
     * @Date 2020/7/7-16:17
     * @param source
     * @param tartgetClass
     * @return T
     */
    public static <T> T copyToTargetObject(Object source, Class<T> tartgetClass) {
        try{
            T targetObj = tartgetClass.newInstance();
            if(CommUtil.isNotNull(source) && CommUtil.isNotNull(tartgetClass)) {
                BeanUtils.copyProperties(source, targetObj);
            }
            return targetObj;
        }catch (Exception e){
            BizUtil.logError(e);
            throw new SdtException(e);
        }
    }

    /**
     * @Description JSON美化
     * @Author sunshaoyu
     * @Date 2020/7/23-13:31
     * @param jsonString
     * @return java.lang.String
     */
    public static String fastjsonBeauty(String jsonString) {
        if(JSON.isValidArray(jsonString)){
            com.alibaba.fastjson.JSONArray fastjsonArray = com.alibaba.fastjson.JSONArray.parseArray(jsonString);
            return JSON.toJSONString(fastjsonArray, true);
        }else{
            com.alibaba.fastjson.JSONObject fastjsonObj = com.alibaba.fastjson.JSONObject.parseObject(jsonString, Feature.OrderedField);
            return JSON.toJSONString(fastjsonObj, true);
        }
    }

    /**
     * @Description list数据去重
     * @Author sunshaoyu
     * @Date 2020/8/5-13:29
     * @param list
     * @return java.util.List<T>
     */
    public static <T> List<T> uniqueList(List<T> list){
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /**
     * @Description 并发执行
     * @Author sunshaoyu
     * @Date 2020/8/12-15:43
     * @param concurrentNum
     * @param timeoutMillis
     * @param callback
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> concurrentExecute(int concurrentNum, long timeoutMillis, Callback callback){
        //返回结果列表
        final Map<String, Object> returnObjMap = new HashMap<String, Object>();

        //执行成功线程返回结果列表
        final List<Object> successThreadReturnList = new LinkedList<Object>();
        //执行失败线程返回结果列表
        final List<Object> errorThreadReturnList = new LinkedList<Object>();

        //定义请求线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<FutureTask<Object>> futureTaskList = new ArrayList<FutureTask<Object>>();

        log.debug("并发任务启动,并发数:{}", concurrentNum);
        //启动线程
        for(int i = 0;i < concurrentNum;i++){
            FutureTask<Object> futureTask = new FutureTask<Object>(new Callable<Object>() {

                @Override
                public Object call() {

                    Object returnObj = null;
                    String curThread = Thread.currentThread().toString();
                    log.debug("线程[" + curThread + "]开始执行");
                    long start = System.currentTimeMillis();
                    boolean successInd = false;

                    try {
                        returnObj = callback.run();
                        successInd = true;
                    }catch (Exception e) {
                        returnObj = CommUtil.nvl(e.getCause().getMessage(), ErrCodeDef.UNKNOWN_ERROR);
                        BizUtil.logError(e);
                    }
                    log.debug("线程[" + Thread.currentThread() + "]执行完成,耗时:" + (System.currentTimeMillis() - start) + "ms");

                    if(successInd){
                        successThreadReturnList.add(returnObj);
                    }else{
                        errorThreadReturnList.add(returnObj);
                    }
                    returnObjMap.put(curThread, returnObj);
                    return returnObj;
                }
            });
            futureTaskList.add(futureTask);
            threadPool.submit(futureTask);
        }

        //等待线程执行完成或超时
        if(awaitThreadPoolFinish(threadPool, timeoutMillis)){
            log.info("并发任务执行完成,作业总数:" + concurrentNum + ",成功作业数:" + successThreadReturnList.size() + ",失败作业数:" + errorThreadReturnList.size());
        }else{
            log.error("并发任务执行超时");
        }
        return returnObjMap;
    }

    /**
     * @Description 等待线程池的任务执行完成
     * @Author sunshaoyu
     * @Date 2020/8/12-15:34
     * @param threadPool
     * @param timeout
     * @return boolean
     */
    public static boolean awaitThreadPoolFinish(ExecutorService threadPool, long timeout){
        threadPool.shutdown();
        long start = System.currentTimeMillis();
        while(!threadPool.isTerminated()){
            if(timeout > 0 && (System.currentTimeMillis() - start) > timeout){
                threadPool.shutdownNow();
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 检查某字符是否为整型
     * @Author sunshaoyu
     * @Date 2020/10/9-13:45
     * @param number
     * @return boolean
     */
    public static boolean isInteger(String number){
        try{
            new Long(number);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * @Author sunshaoyu
     *         <p>
     *         <li>2020年1月3日-下午2:10:51</li>
     *         <li>功能说明：对map进行排序</li>
     *         </p>
     * @param map	map
     * @param comparator	比较器,通过重写比较器的compare方法决定对键或值排序
     * @return
     */
    public static <K,V> Map<K, V> sortMap(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        if(isNull(map)){
            return map;
        }
        Map<K, V> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, comparator);
        Iterator<Map.Entry<K, V>> iterator = entryList.iterator();

        while(iterator.hasNext()){
            Map.Entry<K, V> entry = iterator.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
