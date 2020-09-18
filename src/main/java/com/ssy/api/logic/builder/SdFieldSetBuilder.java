package com.ssy.api.logic.builder;

import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.type.local.SdFieldSetIn;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.defaults.TableType;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Description 字段赋值语句构建
 * @Author sunshaoyu
 * @Date 2020年09月07日-10:14
 */
@Slf4j
public class SdFieldSetBuilder {

    /**
     * @Description 主检查方法
     * @Author sunshaoyu
     * @Date 2020/9/7-10:29
     * @param fieldSetIn
     */
    public static void checkMain(SdFieldSetIn fieldSetIn){
        //必输性校验
        BizUtil.fieldNotNull(fieldSetIn.getSourceEntityId(), SdtDict.A.source_entity_id.getId(), SdtDict.A.source_entity_id.getLongName());
        BizUtil.fieldNotNull(fieldSetIn.getSourceVarName(), SdtDict.A.source_var_name.getId(), SdtDict.A.source_var_name.getLongName());
        BizUtil.fieldNotNull(fieldSetIn.getTargetEntityId(), SdtDict.A.target_entity_id.getId(), SdtDict.A.target_entity_id.getLongName());
        BizUtil.fieldNotNull(fieldSetIn.getSourceVarName(), SdtDict.A.target_var_name.getId(), SdtDict.A.target_var_name.getLongName());

        //复合类型存在性校验
        ComplexType source = OdbFactory.searchComplexType(fieldSetIn.getSourceEntityId());
        ComplexType target = OdbFactory.searchComplexType(fieldSetIn.getTargetEntityId());
        //源字段
        if(CommUtil.isNotNull(source)){
            fieldSetIn.setSourceElementMap(source.getElementMap());
        }else{
            TableType tableType = OdbFactory.searchTable(fieldSetIn.getSourceEntityId());
            if(CommUtil.isNotNull(tableType)){
                fieldSetIn.setSourceElementMap(tableType.getFieldMap());
            }else{
                throw SdtServError.E0022();
            }
        }

        //目标字段
        if(CommUtil.isNotNull(target)){
            fieldSetIn.setTargetElementMap(target.getElementMap());
        }else{
            TableType tableType = OdbFactory.searchTable(fieldSetIn.getTargetEntityId());
            if(CommUtil.isNotNull(tableType)){
                fieldSetIn.setTargetElementMap(tableType.getFieldMap());
            }else{
                throw SdtServError.E0022();
            }
        }
    }

    /**
     * @Description 主处理方法
     * @Author sunshaoyu
     * @Date 2020/9/7-10:31
     * @param fieldSetIn
     * @return java.lang.String
     */
    public static String doMain(SdFieldSetIn fieldSetIn){
        //获取源复合类型和目标复合类型的字段集
        Map<String, Element> sourceElementMap = fieldSetIn.getSourceElementMap();
        Map<String, Element> targetElementMap = fieldSetIn.getTargetElementMap();
        StringBuffer buffer = new StringBuffer();

        targetElementMap.forEach((k, e) -> {
            if(sourceElementMap.containsKey(k)){
                String upperKey = k.substring(0, 1).toUpperCase() + k.substring(1);
                buffer.append(String.format("%s.set%s(%s.get%s());", fieldSetIn.getTargetVarName(), upperKey, fieldSetIn.getSourceVarName(), upperKey)).append("\r\n");
            }
        });
        return buffer.toString();
    }
}
