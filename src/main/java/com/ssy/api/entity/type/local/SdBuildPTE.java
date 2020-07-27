package com.ssy.api.entity.type.local;

import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.meta.defaults.Element;
import com.ssy.api.meta.flowtran.Flowtran;

import java.util.List;

/**
 * @Description 构建PTE json
 * @Author sunshaoyu
 * @Date 2020年07月23日-16:46
 */
public class SdBuildPTE {
    private String flowtranId;
    private E_PTEMODULE pteModule;
    /** 模板为查询表格或可编辑表格时必输 **/
    private String listName;

    //解析过程中的临时变量
    private Flowtran flowtran;
    private List<Element> fieldList;

    public String getFlowtranId() {
        return flowtranId;
    }

    public void setFlowtranId(String flowTranId) {
        this.flowtranId = flowTranId;
    }

    public E_PTEMODULE getPteModule() {
        return pteModule;
    }

    public void setPteModule(E_PTEMODULE pteModule) {
        this.pteModule = pteModule;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Flowtran getFlowtran() {
        return flowtran;
    }

    public void setFlowtran(Flowtran flowtran) {
        this.flowtran = flowtran;
    }

    public List<Element> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Element> fieldList) {
        this.fieldList = fieldList;
    }
}
