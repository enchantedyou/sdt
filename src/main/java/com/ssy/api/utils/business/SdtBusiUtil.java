package com.ssy.api.utils.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.utils.http.HttpUtil;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Description 开发工具助手业务类检查
 * @Author sunshaoyu
 * @Date 2020年06月12日-15:39
 */
@Slf4j
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
     * @Description 检查日期是否合法
     * @Author sunshaoyu
     * @Date 2020/7/5-16:25
     * @param date
     * @param longname
     */
    public static void checkDateValid(String date, String longname) {
        if(CommUtil.isNotNull(date) && !BizUtil.isDateString(date)){
            ApPubErr.E0010(date, longname);
        }
    }

    /**
     * @Description 检查整型是否合法
     * @Author sunshaoyu
     * @Date 2020/7/5-16:38
     * @param intValue
     * @param longname
     */
    public static void checkIntegerValid(Object intValue, String longname) {
        if(CommUtil.isNotNull(intValue) && !BizUtil.isRegexMatches("^[-\\+]?[\\d]*$", String.valueOf(intValue))){
            ApPubErr.E0010(String.valueOf(intValue), longname);
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

    /**
     * @Description 判断是否为微服务模型
     * @Author sunshaoyu
     * @Date 2020/7/17-10:00
     * @param s
     * @return boolean
     */
    public static boolean isMsModel(String s){
        return BizUtil.isRegexMatches(SdtConst.MS_MODEL_REG, s);
    }

    /**
     * @Description 获取小数点左边的内容
     * @Author sunshaoyu
     * @Date 2020/7/23-10:02
     * @param s
     * @return java.lang.String
     */
    public static String getDotLeft(String s){
        String[] component = s.split("\\.");
        if(CommUtil.isNotNull(component) && component.length > 1){
            return component[0];
        }else{
            return null;
        }
    }

    /**
     * @Description 获取小数点右边的内容
     * @Author sunshaoyu
     * @Date 2020/7/23-10:02
     * @param s
     * @return java.lang.String
     */
    public static String getDotRight(String s){
        String[] component = s.split("\\.");
        if(CommUtil.isNotNull(component) && component.length > 1){
            return component[1];
        }else{
            return null;
        }
    }

    /**
     * @Description 打印对象所有get方法的值
     * @Author sunshaoyu
     * @Date 2020/8/12-13:55
     * @param object
     */
    public static void printGetMethodResult(Object object) throws InvocationTargetException, IllegalAccessException {
        for(Method method : object.getClass().getMethods()) {
            if(method.getParameterCount() == 0 && method.getName().startsWith("get")){
                log.info("result of method {{}} is {{}}", method.getName(), method.invoke(object));
            }
        }
    }

    /**
     * @Description 获取核心请求的请求头
     * @Author sunshaoyu
     * @Date 2020/8/12-14:56
     * @param subSystemId
     * @param serviceCode
     * @return com.ssy.api.entity.lang.Params
     */
    public static Params getIcoreHeaders(String subSystemId, String serviceCode){
        Params headers = new Params();
        String trxnSeq = BizUtil.buildTrxnSeq(SdtConst.TRXN_SEQ_LENGTH);
        headers.add("dapplication", subSystemId).add("dserviceid", serviceCode).add("dgroup", "01").add("dversion", "1.0").
                add("api_id", serviceCode).add("busiseqno", trxnSeq).add("callseqno", trxnSeq).add("Content-Type", "application/json");
        return headers;
    }

    /**
     * @Description 谷歌翻译(中翻英)
     * @Author sunshaoyu
     * @Date 2020/9/17-15:28
     * @param content
     * @return java.lang.String
     */
    public static String googleTranslateZhToEn(String content) throws Exception{
        //谷歌翻译接口
        String host = "http://translate.google.cn/";
        String path = "/translate_a/single";

        //查询参数
        Params querys = new Params();
        querys.add("client", "gtx").add("sl", "zh-CN").add("tl", "en").add("dt", "t").add("q", content);
        HttpResponse response = HttpUtil.doGet(host, path, new Params(), querys);

        //解析翻译结果
        JSONArray jsonArray = JSON.parseArray(HttpUtil.resolveResponse(response));
        return String.valueOf(jsonArray.getJSONArray(0).getJSONArray(0).get(0));
    }
}
