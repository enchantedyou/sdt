package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 批量交易状态
 * @Author sunshaoyu
 * @Date 2020/7/5-17:21
 */
public enum E_BATCHEXESTATUS implements DefaultEnum<String> {

    onprocess("onprocess", "onprocess", "待处理"),
    reonprocess("reonprocess", "reonprocess", "暂停并重新就绪"),
    distributing("distributing", "distributing", "正分派"),
    processing("processing", "processing", "处理中"),
    one_commit("one_commit", "one_commit", "一次处理结束，等待异步事件触发二次处理"),
    success("success", "success", "处理成功"),
    failure("failure", "failure", "处理失败"),
    interrupted("interrupted", "interrupted", "已中断"),
    stopped("stopped", "stopped", "已停止"),
    unknown("unknown", "unknown", "未知"),
    ;

    private String id;
    private String value;
    private String longname;

    private E_BATCHEXESTATUS(String id, String value, String longname) {
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
