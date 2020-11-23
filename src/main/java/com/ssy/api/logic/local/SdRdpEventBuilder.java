package com.ssy.api.logic.local;

import com.ssy.api.entity.lang.Params;
import com.ssy.api.entity.sump.component.PTEComponent;
import com.ssy.api.entity.sump.form.*;
import com.ssy.api.exception.SdtException;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description rdp事件生成器
 * @Author sunshaoyu
 * @Date 2020年11月19日-09:39
 */
@Slf4j
public class SdRdpEventBuilder {

    private static final Pattern PARAMS_PATTERN = Pattern.compile("\\$\\{\\{(.+?)\\}\\}");

    public static String build(String jsonName, String fieldName){
        try {
            StringBuilder builder = new StringBuilder(2 << 5);
            PTEComponent pteComponent = SdPTEJsonParser.searchOne(jsonName);
            if(null == pteComponent){
                throw new SdtException("The json file named ["+ jsonName +"] was not searched");
            }

            List<PTEcontrolsGroup> controlsGroupList = pteComponent.getLayout().getForm().getControlsGroup();
            //控件组
            if(CommUtil.isNotNull(controlsGroupList)){
                boolean endLoop = false;
                for(PTEcontrolsGroup controlsGroup : controlsGroupList){
                    List<Map<String, PTEcontrol>> controlList = controlsGroup.getControls();
                    endLoop = traverseControl(fieldName, builder, endLoop, controlList);
                    if(endLoop){
                        break;
                    }
                }
            }else{
                List<Map<String, PTEcontrol>> controlList = pteComponent.getLayout().getForm().getControls();
                traverseControl(fieldName, builder, false, controlList);
            }
            return builder.toString();
        } catch (IOException e) {
            throw new SdtException("Rdp event after domain generation failed", e);
        }
    }

    private static boolean traverseControl(String fieldName, StringBuilder builder, boolean endLoop, List<Map<String, PTEcontrol>> controlList) {
        for(Map<String, PTEcontrol> map : controlList){
            for(Map.Entry<String, PTEcontrol> entry : map.entrySet()){
                if(CommUtil.isNull(fieldName)){
                    handleSingleControl(fieldName, builder, entry);
                }else if(CommUtil.equals(fieldName, entry.getKey())){
                    handleSingleControl(fieldName, builder, entry);
                    endLoop = true;
                    break;
                }
            }
        }
        return endLoop;
    }

    /**
     * @Description 处理单个控件
     * @Author sunshaoyu
     * @Date 2020/11/19-10:08
     * @param fieldName
     * @param builder
     * @param entry
     */
    private static void handleSingleControl(String fieldName, StringBuilder builder, Map.Entry<String, PTEcontrol> entry) {
        PTEevents events = entry.getValue().getEvents();
        String humpExp = toHumpExp(fieldName);

        if(CommUtil.isNotNull(events)){
            Map<String, PTEcontrols> conditionMap = events.getCondition();
            if(CommUtil.isNotNull(conditionMap)){
                builder.append(String.format("var %s = this.getFieldValue(\"%s\");\r\n", humpExp, fieldName));

                //必输栏位
                Map<String, List<String>> mustInputMap = new LinkedHashMap<>();
                //置灰栏位
                Map<String, List<String>> disabledInputMap = new LinkedHashMap<>();
                //可输栏位
                Map<String, List<String>> canInputMap = new LinkedHashMap<>();

                //提取事件属性
                extractEventAttr(conditionMap, mustInputMap, disabledInputMap, canInputMap);
                Set<String> sortSet = new TreeSet<String>((o1, o2) -> CommUtil.compare(o2, o1));
                sortSet.addAll(conditionMap.keySet());
                String firstConditionKey = sortSet.iterator().next();

                for(String conditionKey : sortSet){
                    if(CommUtil.equals(conditionKey, firstConditionKey)){
                        builder.append(String.format("if(this.bSame(\"%s\", %s)){", conditionKey, humpExp)).append("\r\n");
                    }else if(CommUtil.equals(conditionKey, "$default")){
                        builder.append(String.format("else{", conditionKey, humpExp)).append("\r\n");
                    }else{
                        builder.append(String.format("else if(this.bSame(\"%s\", %s)){", conditionKey, humpExp)).append("\r\n");
                    }

                    List<String> mustInputList = mustInputMap.get(conditionKey);
                    List<String> disabledInputList = disabledInputMap.get(conditionKey);
                    List<String> canInputList = canInputMap.get(conditionKey);

                    //必输项
                    if(CommUtil.isNotNull(mustInputList)){
                        if(mustInputList.size() > 1){
                            StringBuilder moreFieldBuilder = new StringBuilder(2 << 4);
                            for(String key : mustInputList){
                                moreFieldBuilder.append("\"").append(key).append("\"").append(",");
                            }
                            builder.append(String.format("\tthis.setMoreFieldMustInput(%s);", moreFieldBuilder.substring(0, moreFieldBuilder.length() - 1))).append("\r\n");
                        }else{
                            builder.append(String.format("\tthis.setFieldMustInput(\"%s\");", mustInputList.get(0))).append("\r\n");
                        }
                    }
                    //置灰项
                    if(CommUtil.isNotNull(disabledInputList)){
                        if(disabledInputList.size() > 1){
                            StringBuilder moreFieldBuilder = new StringBuilder(2 << 4);
                            for(String key : disabledInputList){
                                moreFieldBuilder.append("\"").append(key).append("\"").append(",");
                                builder.append(String.format("\tthis.setFieldValue(\"%s\", \"\");", key)).append("\r\n");
                            }
                            builder.append(String.format("\tthis.setMoreFieldProtect(%s);", moreFieldBuilder.substring(0, moreFieldBuilder.length() - 1))).append("\r\n");
                        }else{
                            builder.append(String.format("\tthis.setFieldValue(\"%s\", \"\");", disabledInputList.get(0))).append("\r\n");
                            builder.append(String.format("\tthis.setFieldProtect(\"%s\");", disabledInputList.get(0))).append("\r\n");
                        }
                    }
                    //可输
                    if(CommUtil.isNotNull(canInputList)){
                        if(canInputList.size() > 1){
                            StringBuilder moreFieldBuilder = new StringBuilder(2 << 4);
                            for(String key : canInputList){
                                moreFieldBuilder.append("\"").append(key).append("\"").append(",");
                            }
                            builder.append(String.format("\tthis.setMoreFieldInput(%s);", moreFieldBuilder.substring(0, moreFieldBuilder.length() - 1))).append("\r\n");
                        }else{
                            builder.append(String.format("\tthis.setFieldInput(\"%s\");", canInputList.get(0))).append("\r\n");
                        }
                    }
                    builder.append("}");
                }
            }

            //域后查询处理
            PTEdoRequest doRequest = events.getDoRequest();
            if(CommUtil.isNotNull(doRequest)){
                builder.append("var inData = {};").append("\r\n");
                Params params = doRequest.getParams();
                String serviceCode = null;

                for(Map.Entry<String, Object> paramEntry : params.entrySet()){
                    String key = paramEntry.getKey();
                    String value = String.valueOf(paramEntry.getValue());

                    if(CommUtil.equals("servicecode", key.toLowerCase())){
                        serviceCode = value;
                    }else{
                        builder.append(String.format("inData.%s = this.getFieldValue(\"%s\");", paramEntry.getKey(), handleDoRequestExp(value))).append("\r\n");
                    }
                }

                //空值返回
                builder.append("\r\n\r\n").append(String.format("if(this.bSpace(inData.%s)){", fieldName)).append("\r\n");
                builder.append("\t").append("return;").append("\r\n");
                builder.append("}\r\n");

                //发起请求
                builder.append(String.format("this.performNONO(\"%s\", inData, function(trxContext){", serviceCode)).append("\r\n");
                builder.append("\tvar outData = trxContext.getInitData();").append("\r\n");

                //输出赋值
                Params resultSet = doRequest.getResultSet();
                for(Map.Entry<String, Object> resultSetEntry : resultSet.entrySet()){
                    builder.append("\t").append(String.format("this.setFieldValue(\"%s\", %s)", resultSetEntry.getKey(), "outData." + resultSetEntry.getValue())).append(";").append("\r\n");
                }

                builder.append("\r\n\tvar func = callBackObj[STRING_S];").append("\r\n");
                builder.append("\tif(func) func();").append("\r\n");
                builder.append("});");
            }
        }
    }

    private static String handleDoRequestExp(String exp){
        Matcher matcher = PARAMS_PATTERN.matcher(exp);
        if(matcher.find()){
            return matcher.group(1).split("\\.")[1];
        }else{
            //throw new SdtException("handle doRequest expression failed");
            return exp;
        }
    }

    private static void extractEventAttr(Map<String, PTEcontrols> conditionMap, Map<String, List<String>> mustInputMap, Map<String, List<String>> disabledInputMap, Map<String, List<String>> canInputMap) {
        for(Map.Entry<String, PTEcontrols> eventEntry : conditionMap.entrySet()){
            String conditionKey = eventEntry.getKey();
            PTEcontrols ptEcontrols = eventEntry.getValue();
            Map<String, PTEcontrol> eventControlMap = ptEcontrols.getControl();

            for(Map.Entry<String, PTEcontrol> controlEntry : eventControlMap.entrySet()){
                String controlKey = controlEntry.getKey();
                PTEcontrol control = controlEntry.getValue();

                //置灰与可输
                if(CommUtil.isNotNull(control.getDisabled()) && control.getDisabled()){
                    putMap(disabledInputMap, conditionKey, controlKey);
                }

                //必输与非必输
                List<PTErule> ruleList = control.getRules();
                if(CommUtil.isNull(ruleList)){
                    continue;
                }else{
                    for(PTErule rule : ruleList){
                        Boolean isRequired = rule.getRequired();
                        if(CommUtil.isNull(isRequired)){
                            continue;
                        }else{
                            if(isRequired){
                                putMap(mustInputMap, conditionKey, controlKey);
                            }else if(CommUtil.isNotNull(control.getDisabled()) && !control.getDisabled()){
                                putMap(canInputMap, conditionKey, controlKey);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void putMap(Map<String, List<String>> map, String conditionKey, String controlKey) {
        List<String> controlFieldList = map.get(conditionKey);
        if(CommUtil.isNull(controlFieldList)){
            controlFieldList = new ArrayList<>();
        }
        controlFieldList.add(controlKey);
        map.put(conditionKey, controlFieldList);
    }

    /**
     * @Description 转驼峰表示法
     * @Author sunshaoyu
     * @Date 2020/11/19-9:52
     * @param fieldName
     * @return java.lang.String
     */
    private static String toHumpExp(String fieldName){
        if(CommUtil.isNull(fieldName)){
            return fieldName;
        }
        StringBuilder builder = new StringBuilder(fieldName.length());
        char[] array = fieldName.toCharArray();
        boolean prevUnderscore = false;

        for(char c : array){
            if('_' == c){
                prevUnderscore = true;
                continue;
            }else if(prevUnderscore){
                builder.append(Character.toUpperCase(c));
            }else{
                builder.append(c);
            }
            prevUnderscore = false;
        }
        return builder.toString();
    }
}
