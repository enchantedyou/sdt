package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description PTE模板
 * @Author sunshaoyu
 * @Date 2020/6/22-18:46
 */
public enum E_PTEMODULE implements DefaultEnum<String> {

    v_inputDataForm_btn("VINPUTDATAFORMBTN", "v_inputDataForm_btn", "a editable form which contains input data and buttons"),
    dynamicTabs("DYNAMICTABS", "dynamicTabs", "dynamic tabs"),
    v_search_btn_datagrid("VSEARCHBTNDATAGRID", "v_search_btn_datagrid", "search datagrid with buttons"),
    v_form_editableDataGrid_btn("VFORMEDITABLEDATAGRID", "v-form-editableDataGrid-btn", "a editable datagrid which contains input data and buttons"),
    v_realonlyForm_btn("READONLY", "v-realonlyForm-btn", "read only form with buttons"),
    ;

    private String id;
    private String value;
    private String longname;

    private E_PTEMODULE(String id, String value, String longname) {
        this.id = id;
        this.value = value;
        this.longname = longname;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getLongName() {
        return this.longname;
    }
}
