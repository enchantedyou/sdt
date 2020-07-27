package com.ssy.api.entity.sump.grid;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.sump.form.PTEdoRequest;

import java.util.List;

/**
 * @Description 数据表格
 * @Author sunshaoyu
 * @Date 2020年06月28日-19:12
 */
public class PTEdatagrid {

    @JSONField(ordinal = 0)
    private Boolean isInitSearch;
    @JSONField(ordinal = 1)
    private String name;
    @JSONField(ordinal = 5)
    private String scope;

    @JSONField(ordinal = 10)
    private PTEdoRequest doRequest;
    @JSONField(ordinal = 15)
    private PTEpagination pagination;

    @JSONField(ordinal = 20)
    private String maxHeight;
    @JSONField(ordinal = 25)
    private String height;

    @JSONField(ordinal = 30)
    private Boolean sortDb;
    @JSONField(ordinal = 35)
    private List<PTEthread> thead;
    @JSONField(ordinal = 40)
    private Boolean showAndHideColumns;

    public Boolean getInitSearch() {
        return isInitSearch;
    }

    public void setInitSearch(Boolean initSearch) {
        isInitSearch = initSearch;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public PTEdoRequest getDoRequest() {
        return doRequest;
    }

    public void setDoRequest(PTEdoRequest doRequest) {
        this.doRequest = doRequest;
    }

    public PTEpagination getPagination() {
        return pagination;
    }

    public void setPagination(PTEpagination pagination) {
        this.pagination = pagination;
    }

    public String getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(String maxHeight) {
        this.maxHeight = maxHeight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Boolean getSortDb() {
        return sortDb;
    }

    public void setSortDb(Boolean sortDb) {
        this.sortDb = sortDb;
    }

    public List<PTEthread> getThead() {
        return thead;
    }

    public void setThead(List<PTEthread> thead) {
        this.thead = thead;
    }

    public Boolean getShowAndHideColumns() {
        return showAndHideColumns;
    }

    public void setShowAndHideColumns(Boolean showAndHideColumns) {
        this.showAndHideColumns = showAndHideColumns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
