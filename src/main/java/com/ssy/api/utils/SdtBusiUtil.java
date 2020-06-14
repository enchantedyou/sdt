package com.ssy.api.utils;

import com.ssy.api.exception.SdtServError;

import java.math.BigDecimal;

/**
 * @Description 开发工具助手业务类检查
 * @Author sunshaoyu
 * @Date 2020年06月12日-15:39
 */
public class SdtBusiUtil {

    private static final String DEFAULT_SPLIT_TOKEN = "-";

    /**
     * @Description 检查金额必须为正数
     * @Author sunshaoyu
     * @Date 2020/6/12-16:05
     * @param amount
     * @param fieldDesc
     */
    public static void checkAmountPositive(BigDecimal amount, String fieldDesc){
        if (CommUtil.isNotNull(amount) && CommUtil.compare(amount, BigDecimal.ZERO) <= 0) {
            SdtServError.E0001(amount.toString(), fieldDesc);
        }
    }

    /**
     * @Description 检查金额不能为负
     * @Author sunshaoyu
     * @Date 2020/6/12-16:07
     * @param amount
     * @param fielddesc
     */
    public static void checkAmountNotNegate(BigDecimal amount, String fielddesc) {
        if (CommUtil.isNotNull(amount) && CommUtil.compare(amount, BigDecimal.ZERO) < 0) {
            SdtServError.E0001(amount.toString(), fielddesc);
        }
    }

    /**
     * @Description 检查从数据库查出的数据是否存在
     * @Author sunshaoyu
     * @Date 2020/6/12-17:08
     * @param obj
     * @param tableDesc
     */
    public static void checkExistenceFromTableQry(Object obj, String tableDesc) {
        if(CommUtil.isNull(obj)){
            SdtServError.E0003(tableDesc);
        }
    }

    /**
     * @Description 将字符串数组转为单个字符串
     * @Author sunshaoyu
     * @Date 2020/6/12-17:36
     * @param token 分隔符
     * @param strArray  字符串数组
     * @return java.lang.String
     */
    public static String parseStrArrayToSingle(String token, String[] strArray){
        token = CommUtil.nvl(token, DEFAULT_SPLIT_TOKEN);
        if(CommUtil.isNull(strArray)){
            return "";
        }else{
            StringBuffer buffer = new StringBuffer();
            for(String str : strArray){
                buffer.append(str).append(token);
            }
            String result = buffer.toString();
            return result.substring(0, result.lastIndexOf(token));
        }
    }

    /**
     * @Description 将字符串数组转为单个字符串
     * @Author sunshaoyu
     * @Date 2020/6/12-17:36
     * @param strArray  字符串数组
     * @return java.lang.String
     */
    public static String parseStrArrayToSingle(String... strArray){
        return parseStrArrayToSingle(null, strArray);
    }
}
