package com.ssy.api.plugins;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.entity.annotation.EncryptedArgument;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.servicetype.SystemParamService;
import com.ssy.api.utils.security.AesEnDecrypt;
import com.ssy.api.utils.security.Md5Encrypt;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月11日-12:36
 */
@Component
@Slf4j
public class SdtArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private SystemParamService systemParamService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(EncryptedArgument.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        try{
            HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

            /** 获取入参和加密秘钥 **/
            String params = request.getParameter(SdtConst.PARAMS);
            String encKey = request.getParameter(SdtConst.ENCKEY);
            String randKey = systemParamService.getValue(SdtConst.AES_RAND_KEY);
            String aesKey = systemParamService.getValue(SdtConst.AES_ENC_KEY);

            if(CommUtil.isNull(params) || CommUtil.isNull(encKey)){
                JSONObject requestBody = getRequestOfJsonType(request);
                params = requestBody.getString(SdtConst.PARAMS);
                encKey = requestBody.getString(SdtConst.ENCKEY);

                //设置分页信息
                BizUtil.getRunEnvs().setCurrentPage(requestBody.getIntValue(SdtConst.CURRENT_PAGE));
                BizUtil.getRunEnvs().setPageSize(requestBody.getIntValue(SdtConst.PAGE_SIZE));
                log.info("Get request parameters from input stream:{}", requestBody.toJSONString());
            }

            /** 参数解密 **/
            String randStr = AesEnDecrypt.aesDecrypt(encKey.substring(SdtConst.ENCKEY_RAND_LENTH), randKey);
            String p3 = AesEnDecrypt.aesDecrypt(params, randKey);
            String p2 = AesEnDecrypt.aesDecrypt(p3, randStr);
            String p = AesEnDecrypt.aesDecrypt(p2, aesKey);

            return resolveArgumentEncrypted(methodParameter, p);
        }catch (Exception e){
            BizUtil.logError(e);
            throw ApPubErr.E0011();
        }
    }

    /**
     * @Description 解析加密的请求参数
     * @Author sunshaoyu
     * @Date 2020/7/11-13:54
     * @param methodParameter
     * @param params
     * @return java.lang.Object
     */
    private Object resolveArgumentEncrypted(MethodParameter methodParameter, String params){
        if(JSON.isValidObject(params)){
            return resolveArgumentForJSONObject(methodParameter, params);
        }else if(JSON.isValidArray(params)){
            return resolveArgumentForJSONArray(methodParameter, params);
        }else{
            throw ApPubErr.E0011();
        }
    }

    /**
     * @Description 处理JSONObject类型的参数
     * @Author sunshaoyu
     * @Date 2020/7/11-13:45
     * @param methodParameter
     * @param p
     * @return java.lang.Object
     */
    private Object resolveArgumentForJSONObject(MethodParameter methodParameter, String p) {
        if(JSON.isValidObject(p)){
            JSONObject jsonObject = argumentFilter(JSON.parseObject(p));
            Object resolvedParam = null;
            if(isBaseClass(methodParameter.getParameterType())){
                resolvedParam = resolveArgumentForBase(jsonObject, methodParameter.getParameterName());
            }else{
                resolvedParam = JSON.parseObject(jsonObject.toJSONString(), methodParameter.getParameterType());
            }
            BizUtil.getRunEnvs().setRequestParams(jsonObject.toJSONString());
            return resolvedParam;
        }
        return null;
    }

    /**
     * @Description 处理JSONArray类型的参数
     * @Author sunshaoyu
     * @Date 2020/7/11-13:34
     * @param methodParameter
     * @param p
     * @return java.lang.Object
     */
    private Object resolveArgumentForJSONArray(MethodParameter methodParameter, String p) {
        Class<?> paramType = methodParameter.getParameterType();
        if(CommUtil.isNotNull(paramType) && CommUtil.isNotNull(p) && CommUtil.isNotNull(paramType.getInterfaces()) && Collection.class.equals(paramType.getInterfaces()[0])){
            List<Class<?>> genericTypeList = getGenericType(methodParameter.getGenericParameterType());
            if(CommUtil.isNotNull(genericTypeList) && JSON.isValidArray(p)){

                String filterJsonArrayStr = argumentFilter(JSON.parseArray(p)).toJSONString();
                BizUtil.getRunEnvs().setRequestParams(filterJsonArrayStr);
                return JSON.parseArray(filterJsonArrayStr, genericTypeList.get(0));
            }
        }
        return null;
    }

    /**
     * @Description 对JSONArray类型的入参进行过滤处理
     * @Author sunshaoyu
     * @Date 2020/7/11-13:14
     * @param jsonArray
     * @return com.alibaba.fastjson.JSONArray
     */
    private JSONArray argumentFilter(JSONArray jsonArray){
        for(int i = 0;i < jsonArray.size();i++) {
            jsonArray.set(i, argumentFilter(jsonArray.getJSONObject(i)));
        }
        return jsonArray;
    }

    /**
     * @Description 对JSONObject类型的入参进行过滤处理
     * @Author sunshaoyu
     * @Date 2020/7/11-13:24
     * @param jsonObject
     */
    private JSONObject argumentFilter(JSONObject jsonObject){
        for(String key : jsonObject.keySet()){
            jsonObject.put(key, xssFilter(jsonObject.getString(key)));
            //敏感数据处理
            for(String sensitiveKey : SdtConst.SENSITIVE_FIELD_ARRAY){
                if(key.toLowerCase().contains(sensitiveKey)){
                    jsonObject.put(key, Md5Encrypt.md5EncodeStr(jsonObject.getString(key)));
                    break;
                }
            }
        }
        return jsonObject;
    }

    /**
     * @Description 放跨站脚本攻击
     * @Author sunshaoyu
     * @Date 2020/7/11-13:22
     * @param paramValue
     * @return java.lang.String
     */
    private String xssFilter(String paramValue) {
        StringBuilder sb = new StringBuilder(paramValue.length() + 16);
        for (int i = 0; i < paramValue.length(); i++) {
            char c = paramValue.charAt(i);
            switch (c) {
                case '>':
                    sb.append("&gt;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '\'':
                    sb.append("&#039;");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '©':
                    sb.append("&copy;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * @Description 获取泛型
     * @Author sunshaoyu
     * @Date 2020/7/11-13:34
     * @param type
     * @return java.util.List<java.lang.Class<?>>
     */
    private List<Class<?>> getGenericType(Type type){
        List<Class<?>> classList = new ArrayList<>();
        if(type instanceof ParameterizedType){
            ParameterizedType pType = (ParameterizedType)type;
            Type[] typeArr = pType.getActualTypeArguments();
            for(Type clazz : typeArr){
                if(clazz instanceof Class){
                    classList.add((Class<?>)clazz);
                }
            }
        }
        return classList;
    }

    /**
     * @Description 判断是否为基础类型
     * @Author sunshaoyu
     * @Date 2020/7/11-13:39
     * @param clazz
     * @return boolean
     */
    private boolean isBaseClass(Class<?> clazz) {
        return clazz.equals(Byte.class)
        || clazz.equals(Short.class)
        || clazz.equals(Integer.class)
        || clazz.equals(Long.class)
        || clazz.equals(Float.class)
        || clazz.equals(Double.class)
        || clazz.equals(Boolean.class)
        || clazz.equals(Character.class)
        || clazz.equals(String.class);
    }

    /**
     * @Description 基础类型参数解析
     * @Author sunshaoyu
     * @Date 2020/7/11-13:42
     * @param obj
     * @param paramName
     * @return java.lang.Object
     */
    private Object resolveArgumentForBase(JSONObject obj, String paramName){
        for(String key : obj.keySet()){
            if(CommUtil.equals(key, paramName)){
                Object value = obj.get(key);
                return (value instanceof String && CommUtil.isNotNull(value)) ? String.valueOf(value).trim() : value;
            }
        }
        return null;
    }

    /**
     * @Description 获取json类型的请求参数
     * @Author sunshaoyu
     * @Date 2020/7/16-16:48
     * @param request
     * @return com.alibaba.fastjson.JSONObject
     */
    private JSONObject getRequestOfJsonType(HttpServletRequest request) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), SdtConst.DEFAULT_ENCODING));
        StringBuilder builder = new StringBuilder();
        String inputStr = null;
        while ((inputStr = streamReader.readLine()) != null){
            builder.append(inputStr);
        }
        return JSON.parseObject(builder.toString());
    }
}
