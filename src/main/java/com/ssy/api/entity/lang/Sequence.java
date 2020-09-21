package com.ssy.api.entity.lang;

/**
 * @Description 序列号
 * @Author sunshaoyu
 * @Date 2020年09月20日-19:44
 */
public class Sequence {

    private Long initValue;
    private Long currentValue;
    private Long cachedSize;
    private Integer maxLength;

    public Sequence(Long initValue, Long currentValue, Long cachedSize, Integer maxLength) {
        this.initValue = initValue;
        this.currentValue = currentValue;
        this.cachedSize = cachedSize;
        this.maxLength = maxLength;
    }

    public Long getInitValue() {
        return initValue;
    }

    public void setInitValue(Long initValue) {
        this.initValue = initValue;
    }

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    public Long getCachedSize() {
        return cachedSize;
    }

    public void setCachedSize(Long cachedSize) {
        this.cachedSize = cachedSize;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "initValue=" + initValue +
                ", currentValue=" + currentValue +
                ", cachedSize=" + cachedSize +
                ", maxLength=" + maxLength +
                '}';
    }
}
