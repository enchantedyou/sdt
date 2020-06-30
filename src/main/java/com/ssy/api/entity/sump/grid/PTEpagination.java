package com.ssy.api.entity.sump.grid;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @Description 分页
 * @Author sunshaoyu
 * @Date 2020年06月28日-09:41
 */
public class PTEpagination {
    @JSONField(ordinal = 0)
    private List<Integer> pageSizes;
    @JSONField(ordinal = 5)
    private Integer pageSize;

    @JSONField(ordinal = 10)
    private Boolean show;
    @JSONField(ordinal = 15)
    private String pageSizeField;

    @JSONField(ordinal = 20)
    private String currentPageField;
    @JSONField(ordinal = 25)
    private Boolean hideOnSinglePage;

    public List<Integer> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<Integer> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getPageSizeField() {
        return pageSizeField;
    }

    public void setPageSizeField(String pageSizeField) {
        this.pageSizeField = pageSizeField;
    }

    public String getCurrentPageField() {
        return currentPageField;
    }

    public void setCurrentPageField(String currentPageField) {
        this.currentPageField = currentPageField;
    }

    public Boolean getHideOnSinglePage() {
        return hideOnSinglePage;
    }

    public void setHideOnSinglePage(Boolean hideOnSinglePage) {
        this.hideOnSinglePage = hideOnSinglePage;
    }
}
