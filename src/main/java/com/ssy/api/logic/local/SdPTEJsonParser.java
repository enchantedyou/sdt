package com.ssy.api.logic.local;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.*;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.entity.sump.component.*;
import com.ssy.api.entity.sump.form.*;
import com.ssy.api.entity.sump.grid.PTEdatagrid;
import com.ssy.api.entity.sump.grid.PTEpagination;
import com.ssy.api.entity.sump.grid.PTEthread;
import com.ssy.api.entity.type.local.SdBuildPTE;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.meta.flowtran.IntfFields;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description PTE json 解析器
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:40
 */
@Component
@Slf4j
public class SdPTEJsonParser {

    private static SdtContextConfig sdtContextConfig;
    private static FileLoader fileLoader;
    private static ModuleMapService moduleMapService;
    private static final ThreadLocal<List<String>> mandatoryLocal = new ThreadLocal<>();

    @Autowired
    public void setSdtContextConfig(SdtContextConfig sdtContextConfig) {
        SdPTEJsonParser.sdtContextConfig = sdtContextConfig;
    }

    @Autowired
    public void setFileLoader(FileLoader fileLoader) {
        SdPTEJsonParser.fileLoader = fileLoader;
    }

    @Autowired
    public void setModuleMapService(ModuleMapService moduleMapService) {
        SdPTEJsonParser.moduleMapService = moduleMapService;
    }

    /** json对象map **/
    private static final Map<String, PTEComponent> pteComponentMap = new ConcurrentHashMap<>();
    /** json文件map **/
    private static final Map<String, PTEComponent> pteJsonMap = new ConcurrentHashMap<>();

    /**
     * @Description 根据模板解析PTE json
     * @Author sunshaoyu
     * @Date 2020/6/22-20:47
     */
    protected static void parsePTEJsonByModule() throws IOException {
        //加载所有的json文件
        Map<String, File> fileMap = fileLoader.load(sdtContextConfig.getSumpResourcePath(), false, "json");
        for(String fileName : fileMap.keySet()){
            String jsonStr = fileLoader.loadAsString(fileMap.get(fileName), SdtConst.DEFAULT_ENCODING);

            if(JSONObject.isValidObject(jsonStr)){
                try{
                    PTEComponent pteComponent = JSONObject.parseObject(jsonStr, PTEComponent.class, Feature.OrderedField);
                    pteComponentMap.put(fileName, pteComponent);
                }catch (JSONException e){
                    log.warn("Deserialization of json file {} failed", fileName);
                }
            }
        }
    }

    /**
     * @Description 检查PTE json是否初始化
     * @Author sunshaoyu
     * @Date 2020/6/23-14:05
     */
    private static void checkPTEInitialize(){
        try {
            if(CommUtil.isNull(pteComponentMap)){
                parsePTEJsonByModule();
            }
        } catch (IOException e) {
            throw new SdtException("Failed to initialize PTE json file", e);
        }
    }

    /**
     * @Description 获取PTEform中的所有PTEcontrol(不包含events中的)
     * @Author sunshaoyu
     * @Date 2020/7/28-16:55
     * @param form
     * @return java.util.List<java.util.Map<java.lang.String,com.ssy.api.entity.sump.form.PTEcontrol>>
     */
    private static List<Map<String, PTEcontrol>> getFormControls(PTEform form){
        List<Map<String, PTEcontrol>> controlList = new ArrayList<>();
        if(CommUtil.isNotNull(form)){
            if(CommUtil.isNotNull(form.getControlsGroup())){
                form.getControlsGroup().forEach(group -> {
                    controlList.addAll(group.getControls());
                });
            }

            if(CommUtil.isNotNull(form.getControls())){
                controlList.addAll(form.getControls());
            }
        }
        return controlList;
    }

    /**
     * @Description 搜索PTE组件
     * @Author sunshaoyu
     * @Date 2020/6/22-21:24
     * @param jsonName  json文件名
     * @return com.ssy.api.entity.sump.component.PTEComponent
     */
    public static PTEComponent searchOne(String jsonName) throws IOException {
        checkPTEInitialize();
        return pteComponentMap.get(jsonName);
    }

    /**
     * @Description 确定当前字段的控制类型
     * @Author sunshaoyu
     * @Date 2020/7/23-14:09
     * @param field
     * @return com.ssy.api.entity.enums.E_CONTROL
     */
    public static E_CONTROL determineControlType(Element field){
        AbstractRestrictionType type = field.getType();
        if(type.getRestriction() == E_RESTRICTION.ENUMTYPE){
            return E_CONTROL.select;
        }else if(type.getRestriction() == E_RESTRICTION.BASETYPE){
            //金额控件
            if(type.getBase() == E_BASE.DECIMAL){
                return E_CONTROL.currency;
            }
            //日期选择器
            if(CommUtil.equals(type.getId(), SdtConst.DATE_BASE_TYPE)){
                return E_CONTROL.dateTimePicker;
            }
            //时间选择器
            else if(CommUtil.equals(type.getId(), SdtConst.TIME_BASE_TYPE)){
                return E_CONTROL.timePicker;
            }
            //币种
            else if(CommUtil.equals(type.getId(), SdtConst.CURRENCY_BASE_TYPE)){
                return E_CONTROL.select;
            }
        }
        return E_CONTROL.text;
    }

    /**
     * @Description 确定字段的最大长度
     * @Author sunshaoyu
     * @Date 2020/7/23-14:20
     * @param field
     * @return int
     */
    public static int determineControlMaxLength(Element field){
        AbstractRestrictionType type = field.getType();
        int len = type.getMaxLength();
        if(type.getMaxLength() > 0){
            return (type.getRestriction() == E_RESTRICTION.BASETYPE && type.getBase() == E_BASE.DECIMAL) ? CommUtil.getSmaller(len, 16) : len;
        }else if(type.getRestriction() == E_RESTRICTION.ENUMTYPE){
            //计算枚举值的最大长度
            Map<String, DefaultEnumerationType> enumMap = type.getEnumerationMap();
            int max = 0;
            for(Map.Entry<String, DefaultEnumerationType> entry : enumMap.entrySet()){
                String value = entry.getValue().getValue();
                if(value.length() > max){
                    max = value.length();
                }
            }
            //保证最大值是5的倍数
            return max + (5 - (max % 5));
        }else{
            log.warn("Unable to determine the maximum length of field {}", field.getId());
            return 0;
        }
    }

    /**
     * @Description 构建组件PTElayout
     * @Author sunshaoyu
     * @Date 2020/7/23-15:56
     * @param buildPTE
     * @return com.ssy.api.entity.sump.component.PTElayout
     */
    public static PTElayout buildComponentLayout(SdBuildPTE buildPTE){
        PTElayout layout = new PTElayout();
        /** 查询表格 | 可编辑表格 **/
        if(buildPTE.getPteModule() == E_PTEMODULE.v_search_btn_datagrid || buildPTE.getPteModule() == E_PTEMODULE.v_form_editableDataGrid_btn){
            layout.setForm(buildComponentForm(buildPTE));
            layout.setDatagrid(buildComponentDatagrid(buildPTE));

            //工具栏
            if(buildPTE.getPteModule() == E_PTEMODULE.v_form_editableDataGrid_btn){
                layout.setToolbar(buildComponentToolbar(buildPTE));
            }
        }
        /** 只读表单 | 可编辑表单 **/
        else if(buildPTE.getPteModule() == E_PTEMODULE.v_inputDataForm_btn || buildPTE.getPteModule() == E_PTEMODULE.v_realonlyForm_btn){
            layout.setForm(buildComponentForm(buildPTE));
        }
        /** 选项卡 **/
        else if(buildPTE.getPteModule() == E_PTEMODULE.dynamicTabs){
            layout.setTabs(buildComponentTabs(buildPTE));
        }
        return layout;
    }

    /**
     * @Description 构建PTE组件PTEfooter
     * @Author sunshaoyu
     * @Date 2020/7/27-16:40
     * @param buildPTE
     * @return com.ssy.api.entity.sump.component.PTEfooter
     */
    public static PTEfooter buildComponentFooter(SdBuildPTE buildPTE){
        PTEfooter footer = new PTEfooter();
        footer.setBtns(buildComponentBtnList(buildPTE));
        return footer;
    }

    /**
     * @Description 构建PTE组件PTEtabs
     * @Author sunshaoyu
     * @Date 2020/7/27-16:38
     * @param buildPTE
     * @return com.ssy.api.entity.sump.component.PTEtabs
     */
    public static PTEtabs buildComponentTabs(SdBuildPTE buildPTE){
        PTEtabs tabs = new PTEtabs();
        tabs.setScope(SdtConst.DEFAULT_TABS_SCOPE);
        tabs.setInitDataOnce(true);
        tabs.setSubmitBtnPlace(E_POSITION.right);

        tabs.setDoRequest(buildComponentDoRequest(buildPTE.getFlowtran()));
        tabs.setTabHeader(buildComponentTabHeaderList(buildPTE));
        tabs.setDefaultActiveKey(CommUtil.isNull(tabs.getTabHeader()) ? "" : tabs.getTabHeader().get(0).getId());
        tabs.setPosition(E_POSITION.top);

        tabs.setStyleClass("card");
        tabs.setBtns(buildComponentBtnList(buildPTE));
        return tabs;
    }

    /**
     * @Description 构建PTE组件PTEtabHeader的list
     * @Author sunshaoyu
     * @Date 2020/7/27-16:37
     * @param buildPTE
     * @return java.util.List<com.ssy.api.entity.sump.component.PTEtabHeader>
     */
    public static List<PTEtabHeader> buildComponentTabHeaderList(SdBuildPTE buildPTE){
        //暂时用输入接口的字段
        List<IntfFields> fieldsList = buildPTE.getFlowtran().getInput().getFieldsList();
        List<PTEtabHeader> headerList = new ArrayList<>();

        for(IntfFields fields : fieldsList){
            PTEtabHeader header = new PTEtabHeader();
            header.setId(fields.getId());
            header.setLabel(fields.getLongName());

            header.setClosable(false);
            header.setDisabled(false);
            header.setIcon("el-icon-date");
            header.setToRequest(buildComponentToRequest("url", E_REQUESTMETHOD.GET, null));
            headerList.add(header);
        }
        return headerList;
    }

    /**
     * @Description 构建组件PTEform
     * @Author sunshaoyu
     * @Date 2020/7/23-15:00
     * @param buildPTE
     * @return com.ssy.api.entity.sump.form.PTEform
     */
    public static PTEform buildComponentForm(SdBuildPTE buildPTE){
        PTEform form = new PTEform();
        E_PTEMODULE pteModule = buildPTE.getPteModule();
        /** 查询表格 **/
        if(pteModule == E_PTEMODULE.v_search_btn_datagrid){
            form.setFoldLineNumber(SdtConst.FOLD_LINE_NUMBER);
            form.setInitSearch(false);
            form.setControls(buildComponentFormControls(buildPTE));
        }
        /** 只读表单 **/
        else if(pteModule == E_PTEMODULE.v_realonlyForm_btn){
            form.setInitRequest("${{params}}");
            form.setBtns(buildComponentBtnList(buildPTE));
            form.setControls(buildComponentFormControls(buildPTE));
        }
        /** 可输入表单 **/
        else if(pteModule == E_PTEMODULE.v_inputDataForm_btn){
            form.setDoRequest(buildComponentDoRequest(buildPTE.getFlowtran()));
            form.setBtns(buildComponentBtnList(buildPTE));
            form.setControls(buildComponentFormControls(buildPTE));
        }else{
            return null;
        }
        return form;
    }

    /**
     * @Description 构建PTE组件PTEtoolbar
     * @Author sunshaoyu
     * @Date 2020/7/27-15:49
     * @param buildPTE
     * @return com.ssy.api.entity.sump.component.PTEtoolbar
     */
    public static PTEtoolbar buildComponentToolbar(SdBuildPTE buildPTE){
        PTEtoolbar toolbar = new PTEtoolbar();
        if(buildPTE.getPteModule() == E_PTEMODULE.v_form_editableDataGrid_btn){
            toolbar.setToolbar(buildComponentBtnList(buildPTE));
        }else{
            return null;
        }
        return toolbar;
    }

    /**
     * @Description 构建组件PTEcontrols(form表单的输入要素)
     * @Author sunshaoyu
     * @Date 2020/7/23-14:45
     * @param buildPTE
     * @return java.util.List<java.util.Map<java.lang.String,com.ssy.api.entity.sump.form.PTEcontrol>>
     */
    public static List<Map<String, PTEcontrol>> buildComponentFormControls(SdBuildPTE buildPTE){
        List<Element> fieldList = buildPTE.getFlowtran().getInput().getFieldList();
        List<Map<String, PTEcontrol>> controlList = new ArrayList<>();

        /** 只读表单 **/
        if(buildPTE.getPteModule() == E_PTEMODULE.v_realonlyForm_btn){
            fieldList = buildPTE.getFlowtran().getOutput().getFieldList();
            if(CommUtil.isNull(fieldList)){
                fieldList= buildPTE.getFlowtran().getOutput().getFieldsList().get(0).getSubFieldList();
            }
        }

        Map<String, PTEcontrol> map = new LinkedHashMap<>();
        for(Element e : fieldList){
            map.put(e.getId(), buildComponentControl(e, buildPTE.getPteModule()));
        }
        controlList.add(map);
        return controlList;
    }

    /**
     * @Description 构建组件PTEcontrol
     * @Author sunshaoyu
     * @Date 2020/7/23-14:43
     * @param field
     * @param pteModule
     * @return com.ssy.api.entity.sump.form.PTEcontrol
     */
    public static PTEcontrol buildComponentControl(Element field, E_PTEMODULE pteModule){
        PTEcontrol control = new PTEcontrol();
        /** 查询表格 | 输入表单 **/
        if(pteModule == E_PTEMODULE.v_search_btn_datagrid || pteModule == E_PTEMODULE.v_inputDataForm_btn){
            control.setLabel(field.getDesc());
            control.setControl(determineControlType(field));
            control.setRules(buildComponentRules(field, control.getControl(), CommUtil.isNotNull(mandatoryLocal.get()) && mandatoryLocal.get().contains(field.getId())));
            control.setDisabled(false);
            control.setDict(buildComponentDict(field));

            //金额控件处理
            if(control.getControl() == E_CONTROL.currency){
                control.setValue("");
                control.setDecimal(SdtConst.DECIMAL_DIGIT);
                control.setThousand(',');
                control.setMax(determineControlMaxLength(field));
            }
        }
        /** 只读表单 **/
        else if(pteModule == E_PTEMODULE.v_realonlyForm_btn){
            control.setLabel(field.getDesc());
            control.setControl(E_CONTROL.string);
            control.setDict(buildComponentDict(field));
        }
        return control;
    }

    /**
     * @Description 构建组件PTErule
     * @Author sunshaoyu
     * @Date 2020/7/23-14:39
     * @param field
     * @param control
     * @param required
     * @return java.util.List<com.ssy.api.entity.sump.form.PTErule>
     */
    private static List<PTErule> buildComponentRules(Element field, E_CONTROL control, boolean required){
        List<PTErule> ruleList = new ArrayList<>();
        PTErule requiredRule = new PTErule();
        PTErule limitRule = new PTErule();
        requiredRule.setRequired(required);

        if(control == E_CONTROL.lookup || control == E_CONTROL.select || control == E_CONTROL.remoteSelect || control == E_CONTROL.radio
        || control == E_CONTROL.checkbox || control == E_CONTROL.dateTimePicker || control == E_CONTROL.timePicker){
            if(required){
                requiredRule.setMessage(String.format("请选择%s", field.getDesc()));
            }
            ruleList.add(requiredRule);
        }else{
            if(required){
                requiredRule.setMessage(String.format("请输入%s", field.getDesc()));
            }
            ruleList.add(requiredRule);

            if(control == E_CONTROL.currency){
                limitRule.setPattern(SdtConst.CURRENCY_REG);
                limitRule.setMessage("请输入正确的金额");
            }else if(control != E_CONTROL.lookup){
                limitRule.setMax(determineControlMaxLength(field));
                limitRule.setMessage(String.format("长度不能超过%d个字符", limitRule.getMax()));
            }

            if(CommUtil.isNotNull(limitRule.getMessage())){
                ruleList.add(limitRule);
            }
        }
        return CommUtil.nvl(ruleList, null);
    }


    /**
     * @Description 构建组件PTEdoRequest
     * @Author sunshaoyu
     * @Date 2020/7/23-15:08
     * @param flowtran
     * @return com.ssy.api.entity.sump.form.PTEdoRequest
     */
    public static PTEdoRequest buildComponentDoRequest(Flowtran flowtran){
        PTEdoRequest doRequest = new PTEdoRequest();
        doRequest.setUrl(SdtConst.DEFAULT_REQUEST_URL);
        doRequest.setMethod(E_REQUESTMETHOD.POST);
        doRequest.setParams(new Params().add(SdtDict.A.service_code.getId(), moduleMapService.getServiceCode(flowtran.getId())));
        return doRequest;
    }

    /**
     * @Description 构建组件PTEdoRequest(重载)
     * @Author sunshaoyu
     * @Date 2020/7/23-15:26
     * @param params
     * @return com.ssy.api.entity.sump.form.PTEdoRequest
     */
    public static PTEdoRequest buildComponentDoRequest(Params params){
        PTEdoRequest doRequest = new PTEdoRequest();
        doRequest.setUrl(SdtConst.DEFAULT_REQUEST_URL);
        doRequest.setMethod(E_REQUESTMETHOD.POST);
        doRequest.setParams(params);
        return doRequest;
    }

    /**
     * @Description 构建组件PTEthread的列表
     * @Author sunshaoyu
     * @Date 2020/7/23-15:51
     * @param buildPTE
     * @return java.util.List<com.ssy.api.entity.sump.grid.PTEthread>
     */
    public static List<PTEthread> buildComponentThead(SdBuildPTE buildPTE){
        E_PTEMODULE pteModule = buildPTE.getPteModule();
        List<Element> fieldList = buildPTE.getFieldList();

        List<PTEthread> threadList = new ArrayList<>();
        /** 查询表格(头部) **/
        if(pteModule == E_PTEMODULE.v_search_btn_datagrid){
            PTEthread idxThread = new PTEthread();
            idxThread.setType(E_THREADTYPE.index);
            idxThread.setLabel("#");
            threadList.add(idxThread);
        }
        for(Element e : fieldList){
            PTEthread thread = new PTEthread();
            thread.setLabel(e.getDesc());
            thread.setProp(e.getId());
            thread.setDict(buildComponentDict(e));

            //金额字段处理
            if(e.getType().getBase() == E_BASE.DECIMAL){
                thread.setFormat(new Params().add("decimal", e.getType().getFractionDigits()).add("decimalPoint",".").add("thousandsPoint",","));
            }
            //日期处理
            else if(CommUtil.equals(e.getType().getId(), SdtConst.DATE_BASE_TYPE)){
                thread.setType(E_THREADTYPE.dateTimePicker);
            }
            //时间处理
            else if(CommUtil.equals(e.getType().getId(), SdtConst.TIME_BASE_TYPE)){
                thread.setType(E_THREADTYPE.timePicker);
            }
            threadList.add(thread);
        }
        /** 查询表格(尾部) **/
        if(pteModule == E_PTEMODULE.v_search_btn_datagrid || pteModule == E_PTEMODULE.v_form_editableDataGrid_btn){
            PTEthread footThread = new PTEthread();
            footThread.setLabel("操作");
            footThread.setFixed(E_POSITION.right);

            footThread.setAlign(E_POSITION.center);
            footThread.setBtns(buildComponentBtnList(buildPTE, true));
            if(pteModule == E_PTEMODULE.v_form_editableDataGrid_btn){
                footThread.setWidth("120px");
            }

            threadList.add(footThread);
        }
        return threadList;
    }

    /**
     * @Description 构建组件PTEdict
     * @Author sunshaoyu
     * @Date 2020/7/23-15:19
     * @param field
     * @return com.ssy.api.entity.sump.form.PTEdict
     */
    public static PTEdict buildComponentDict(Element field){
        PTEdict dict = new PTEdict();
        List<String> dictKeyList = new ArrayList<>();

        if(field.getType().getRestriction() == E_RESTRICTION.ENUMTYPE){
            dict.setDictType(field.getType().getId());
            dictKeyList.add(dict.getDictType());

            dict.setDictKey(dictKeyList);
            dict.setFormat(SdtConst.DEFAULT_DICT_FORMAT);
            return dict;
        }else if(field.getType().getRestriction() == E_RESTRICTION.BASETYPE && CommUtil.equals(field.getType().getId(), SdtConst.CURRENCY_BASE_TYPE)){
            //金额控件下拉固定写法
            dict.setDictType("E_CCYCODE");
            dictKeyList.add(dict.getDictType());
            dict.setDictKey(dictKeyList);
            dict.setFormat(SdtConst.DEFAULT_DICT_FORMAT);

            dict.setLabelField("drop_list_desc");
            dict.setValueField("drop_list_value");
            dict.setDoRequest(buildComponentDoRequest(new Params().add(SdtDict.A.service_code.getId(), "ms1053").add("dynamic_drop_list_type","AP0011")));
            return dict;
        }
        return null;
    }

    /**
     * @Description 构建组件PTEbtns的列表
     * @Author sunshaoyu
     * @Date 2020/7/23-15:50
     * @param buildPTE
     * @return java.util.List<com.ssy.api.entity.sump.component.PTEbtns>
     */
    public static List<PTEbtns> buildComponentBtnList(SdBuildPTE buildPTE){
        return buildComponentBtnList(buildPTE, false);
    }

    /**
     * @Description 构建组件PTEbtns的列表
     * @Author sunshaoyu
     * @Date 2020/7/27-15:16
     * @param buildPTE
     * @param footer
     * @return java.util.List<com.ssy.api.entity.sump.component.PTEbtns>
     */
    public static List<PTEbtns> buildComponentBtnList(SdBuildPTE buildPTE, boolean footer){
        List<PTEbtns> btnList = new ArrayList<>();
        E_PTEMODULE pteModule = buildPTE.getPteModule();
        /** 查询表格 **/
        if(pteModule == E_PTEMODULE.v_search_btn_datagrid){
            btnList.add(buildComponentBtn(buildPTE, E_BTNTYPE.look));
        }
        /** 输入表单 **/
        else if(pteModule == E_PTEMODULE.v_inputDataForm_btn){
            btnList.add(buildComponentBtn(buildPTE, E_BTNTYPE.submit));
        }
        /** 可编辑表格 **/
        else if(pteModule == E_PTEMODULE.v_form_editableDataGrid_btn){
            if(!footer){
                btnList.add(buildComponentBtn(buildPTE, E_BTNTYPE.add));
            }else{
                btnList.add(buildComponentBtn(buildPTE, E_BTNTYPE.edit));
                btnList.add(buildComponentBtn(buildPTE, E_BTNTYPE.delete));
            }
        }
        /** 选项卡 **/
        else if(pteModule == E_PTEMODULE.dynamicTabs){
            btnList.add(buildComponentBtn(buildPTE, E_BTNTYPE.submit));
        }
        return btnList;
    }

    /**
     * @Description 构建PTE组件PTEbtns
     * @Author sunshaoyu
     * @Date 2020/7/27-15:33
     * @param btnType
     * @return com.ssy.api.entity.sump.component.PTEbtns
     */
    public static PTEbtns buildComponentBtn(SdBuildPTE buildPTE, E_BTNTYPE btnType){
        PTEbtns btn = new PTEbtns();
        btn.setType(btnType);

        if(btnType == E_BTNTYPE.submit){
            btn.setLabel("提交");
        }else if(btnType == E_BTNTYPE.look){
            btn.setLabel("详情");
            btn.setToRequest(buildComponentToRequest("url", E_REQUESTMETHOD.GET, null));
        }else if(btnType == E_BTNTYPE.add){
            btn.setLabel("新增");
            btn.setIcon("el-icon-plus");
            btn.setToRequest(buildComponentToRequest("url", E_REQUESTMETHOD.GET, null));
            btn.setProps(new Params().add("width", "65%").add("title", "新增页面标题"));
        }else if(btnType == E_BTNTYPE.edit){
            btn.setLabel("维护");
            btn.setToRequest(buildComponentToRequest("url", E_REQUESTMETHOD.GET, null));
            btn.setProps(new Params().add("width", "65%").add("title", "维护页面标题"));
        }else if(btnType == E_BTNTYPE.delete){
            btn.setLabel("删除");
            btn.setIcon("el-icon-close");
        }
        return btn;
    }

    /**
     * @Description 构建组件PTEtoRequest
     * @Author sunshaoyu
     * @Date 2020/7/23-15:48
     * @param url
     * @param method
     * @param params
     * @return com.ssy.api.entity.sump.form.PTEtoRequest
     */
    public static PTEtoRequest buildComponentToRequest(String url, E_REQUESTMETHOD method, Params params){
        PTEtoRequest toRequest = new PTEtoRequest();
        toRequest.setMethod(method);
        toRequest.setUrl(url);
        if(CommUtil.isNotNull(params)){
            toRequest.setParams(params);
        }
        return toRequest;
    }

    /**
     * @Description 构建组件PTEdatagrid
     * @Author sunshaoyu
     * @Date 2020/7/23-15:54
     * @param buildPTE
     * @return com.ssy.api.entity.sump.grid.PTEdatagrid
     */
    public static PTEdatagrid buildComponentDatagrid(SdBuildPTE buildPTE){
        PTEdatagrid datagrid = new PTEdatagrid();
        PTEpagination pagination = new PTEpagination();

        /** 查询表格 **/
        if(buildPTE.getPteModule() == E_PTEMODULE.v_search_btn_datagrid){
            pagination.setShow(true);
            datagrid.setInitSearch(false);
            datagrid.setDoRequest(buildComponentDoRequest(buildPTE.getFlowtran()));
        }
        /** 可编辑表格 **/
        else if(buildPTE.getPteModule() == E_PTEMODULE.v_form_editableDataGrid_btn){
            pagination.setShow(false);
            datagrid.setName(buildPTE.getListName());
        }
        datagrid.setPagination(pagination);
        datagrid.setThead(buildComponentThead(buildPTE));
        return datagrid;
    }

    /**
     * @Description 根据模板构建PTE Component
     * @Author sunshaoyu
     * @Date 2020/7/23-16:48
     * @param buildPTE
     * @return java.lang.String
     */
    public static PTEComponent buildPTEComponent(SdBuildPTE buildPTE){
        BizUtil.fieldNotNull(buildPTE.getFlowtranId(), SdtDict.A.flowtran_id.getId(), SdtDict.A.flowtran_id.getLongName());
        BizUtil.fieldNotNull(buildPTE.getPteModule(), SdtDict.A.pte_module.getId(), SdtDict.A.pte_module.getLongName());

        //获取接口输入输出集
        Flowtran flowtran = SdFlowtranParser.load(buildPTE.getFlowtranId());
        buildPTE.setFlowtran(flowtran);
        PTEComponent pteComponent = new PTEComponent();

        //必输字段集
        mandatoryLocal.set(SdJavaParser.searchMandatoryFields(buildPTE.getFlowtranId()));

        //获取字段列表
        if(buildPTE.getPteModule() == E_PTEMODULE.v_search_btn_datagrid || buildPTE.getPteModule() == E_PTEMODULE.v_form_editableDataGrid_btn){
            BizUtil.fieldNotNull(buildPTE.getListName(), SdtDict.A.list_name.getId(), SdtDict.A.list_name.getLongName());
            //从输入接口中检索
            if(!determineListFields(buildPTE, flowtran, E_IO.INPUT)){
                //从输出接口中检索
                if(!determineListFields(buildPTE, flowtran, E_IO.OUTPUT)){
                    throw SdtServError.E0016(buildPTE.getListName());
                }
            }
        }

        //开始构建
        pteComponent.setModule(buildPTE.getPteModule());
        pteComponent.setLayout(buildComponentLayout(buildPTE));
        return pteComponent;
    }

    /**
     * @Description 根据模板构建PTE json
     * @Author sunshaoyu
     * @Date 2020/7/28-17:04
     * @param buildPTE
     * @return java.lang.String
     */
    public static String buildPTEJson(SdBuildPTE buildPTE){
        return CommUtil.fastjsonBeauty(JSON.toJSONString(buildPTEComponent(buildPTE)));
    }

    /**
     * @Description 确定list字段的字段列表
     * @Author sunshaoyu
     * @Date 2020/7/27-15:57
     * @param buildPTE
     * @param flowtran
     * @param io
     */
    private static boolean determineListFields(SdBuildPTE buildPTE, Flowtran flowtran, E_IO io) {
        List<IntfFields> intfFields = io == E_IO.INPUT ? flowtran.getInput().getFieldsList() : flowtran.getOutput().getFieldsList();
        for(IntfFields listField : intfFields){
            if(listField.isMulti() && CommUtil.equals(listField.getId(), buildPTE.getListName())){
                buildPTE.setFieldList(listField.getSubFieldList());
                return true;
            }
        }
        return false;
    }

    /**
     * @Description 获取PTE json的文件名
     * @Author sunshaoyu
     * @Date 2020/7/24-10:02
     * @param buildPTE
     * @return java.lang.String
     */
    public static String getPTEJsonFileName(SdBuildPTE buildPTE){
        return buildPTE.getFlowtranId() + "-" + buildPTE.getPteModule() + ".json";
    }
}
