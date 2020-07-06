package com.ssy.api.utils;

import com.ssy.api.entity.enums.E_STRGENTYPE;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description 公共工具类
 * @Author sunshaoyu
 * @Date 2020年06月11日-14:06
 */
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
            return ((String) obj).length() == 0 || ((String) obj).equals("null");
        } else if (obj instanceof Map) {
            obj = Map.class.cast(obj);
            Map<?, ?> tmpObj = (Map<?, ?>) obj;
            return tmpObj.isEmpty();
        } else if (obj instanceof Collection<?>) {
            obj = Collection.class.cast(obj);
            Collection<?> tmpObj = (Collection<?>) obj;
            return tmpObj.isEmpty();
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
                    throw new RuntimeException(e);
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
}
