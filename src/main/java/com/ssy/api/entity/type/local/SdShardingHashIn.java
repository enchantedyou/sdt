package com.ssy.api.entity.type.local;

/**
 * @Description 查询分片哈希值输入复合类型
 * @Author sunshaoyu
 * @Date 2020年10月10日-09:52
 */
public class SdShardingHashIn {

    private Integer upperLimit;
    private String sequence;

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
