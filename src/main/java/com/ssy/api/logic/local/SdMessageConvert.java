package com.ssy.api.logic.local;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 报文转换为单元测试代码
 * @Author sunshaoyu
 * @Date 2020年10月23日-13:59
 */
@Component
@Slf4j
public class SdMessageConvert {

    @Autowired
    private ModuleMapService moduleMapService;

    public String toUnitTestCode(String requestBody){
        BizUtil.fieldNotNull(requestBody, SdtDict.A.request_body.getId(), SdtDict.A.request_body.getLongName());
        StringBuilder builder = new StringBuilder();
        try{
            JSONObject jsonObj = JSON.parseObject(requestBody);
            Flowtran flowtran = SdFlowtranParser.load(moduleMapService.getInnerServiceCode(jsonObj.getJSONObject("sys").getString("servicecode")));
            JSONObject inputJsonObj = jsonObj.getJSONObject("input");

            List<Element> inputList = flowtran.getServiceList().get(0).getServiceInput();
            if(inputList.size() > 1){
                //不支持散字段
                throw new UnsupportedOperationException();
            }else if(CommUtil.isNotNull(inputList)){
                Element e = inputList.get(0);
                String complexId = e.getType().getId();
                Map<String, Element> elementMap = e.getType().getElementMap();

                //传入的值为列表
                if(e.isMulti()){
                    genOptionsStatement(builder, complexId, inputJsonObj.getJSONArray(e.getId()).toString(), null);
                }else{
                    builder.append(e.getType().getId() + " " + e.getId() + " = " + "BizUtil.getInstance("+e.getType().getId()+".class);\r\n");
                    //构建赋值语句
                    for(Map.Entry<String, Object> entry : inputJsonObj.entrySet()){
                        String baseStatement = e.getId() + ".set" + entry.getKey().substring(0,1).toUpperCase() + entry.getKey().substring(1);
                        Element dict = elementMap.get(entry.getKey());

                        if(CommUtil.isNotNull(dict)){
                            if(!dict.isMulti()){
                                generateSetStatement(builder, entry.getValue(), dict, baseStatement);
                            }else{
                                genOptionsStatement(builder, dict.getType().getId(), String.valueOf(entry.getValue()), baseStatement);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new SdtException("The conversion to unit test code failed, cause by:" + e.getCause(), e);
        }
        return builder.toString();
    }

    /**
     * @Description 生成列表赋值语句
     * @Author sunshaoyu
     * @Date 2020/10/23-15:44
     * @param builder
     * @param complexId
     * @param listValue
     * @param baseStatement
     */
    private void genOptionsStatement(StringBuilder builder, String complexId, String listValue, String baseStatement) {
        //实例化Options
        builder.append("\r\nOptions<"+complexId+"> "+complexId+"Options = new DefaultOptions<"+complexId+">();\r\n");
        JSONArray jsonArray = JSON.parseArray(listValue);

        for(int i = 0;i < jsonArray.size();i++){
            JSONObject subJsonObj = jsonArray.getJSONObject(i);
            //实例化子类
            String subVarName = complexId+"SubInstance"+(i+1);
            builder.append(complexId + " "+subVarName+" = BizUtil.getInstance("+complexId+".class);\r\n");
            for(Object subKey : subJsonObj.keySet()){
                generateSetStatement(builder, subJsonObj.get(subKey), OdbFactory.searchDict(String.valueOf(subKey)), subVarName + ".set" + subKey.toString().substring(0,1).toUpperCase() + subKey.toString().substring(1));
            }
            builder.append(complexId+"Options"+".add("+subVarName+");\r\n");
        }

        if(CommUtil.isNotNull(baseStatement)){
            builder.append(baseStatement + "("+complexId+"Options"+");\r\n");
        }
    }

    /**
     * @Description 生成基础赋值语句
     * @Author sunshaoyu
     * @Date 2020/10/23-15:38
     * @param builder
     * @param value
     * @param dictInfo
     * @param baseStatement
     */
    private static void generateSetStatement(StringBuilder builder, Object value, Element dictInfo, String baseStatement) {
        if(CommUtil.isNull(value)){
            builder.append(baseStatement + "(null);\r\n");
        }else if(dictInfo.getType().getRestriction() == E_RESTRICTION.BASETYPE){
            String baseTypeValue = dictInfo.getType().getBase().getValue();
            //如果字段是基础引用类型
            if("decimal".equals(baseTypeValue)){
                builder.append(baseStatement + "(BigDecimal.valueOf("+value+"));\r\n");
            }else if("long".equals(baseTypeValue)){
                builder.append(baseStatement + "("+value+"L);\r\n");
            }else{
                builder.append(baseStatement + "(\""+value+"\");\r\n");
            }
        }else{
            //如果字段是枚举
            DefaultEnumerationType enumType = dictInfo.getType().getEnumerationMap().get(value);
            if(CommUtil.isNull(enumType)){
                builder.append(baseStatement + "(\""+value+"\");\r\n");
            }else{
                builder.append(baseStatement + "("+dictInfo.getType().getId()+"."+enumType.getId()+");\r\n");
            }
        }
    }
}
