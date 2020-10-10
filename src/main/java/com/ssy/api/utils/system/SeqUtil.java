package com.ssy.api.utils.system;

import com.ssy.api.dao.mapper.local.SdpSequenceBuilderMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.lang.Sequence;
import com.ssy.api.entity.table.local.SdpSequenceBuilder;
import com.ssy.api.utils.business.SdtBusiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 序列号工具类
 * @Author sunshaoyu
 * @Date 2020年09月20日-19:30
 */
@Component
@Slf4j
public class SeqUtil {

    /** 序号缓存区 **/
    private static final Map<String, Sequence> sequenceMap = new ConcurrentHashMap<>();
    private static SdpSequenceBuilderMapper sequenceBuilderMapper;

    @Autowired
    public void setSequenceBuilderMapper(SdpSequenceBuilderMapper sequenceBuilderMapper) {
        this.sequenceBuilderMapper = sequenceBuilderMapper;
    }

    /**
     * @Description 生成序列号
     * @Author sunshaoyu
     * @Date 2020/9/20-20:05
     * @param seqCode
     * @return java.lang.String
     */
    public synchronized static String genSeq(String seqCode){
        BizUtil.fieldNotNull(seqCode, SdtDict.A.seq_code.getId(), SdtDict.A.seq_code.getLongName());
        Sequence sequence = sequenceMap.get(seqCode);
        final Long gap = 1L;

        if(CommUtil.isNull(sequence) || sequence.getCachedSize() <= 0){
            SdpSequenceBuilder sequenceBuilder = sequenceBuilderMapper.selectByPrimaryKey(seqCode, true);
            SdtBusiUtil.checkNumberNotNegate(sequenceBuilder.getInitValue(), SdtDict.A.init_value.getLongName());
            SdtBusiUtil.checkNumberNotNegate(sequenceBuilder.getCurrentValue(), SdtDict.A.current_value.getLongName());
            SdtBusiUtil.checkNumberPositive(sequenceBuilder.getSeqLength(), SdtDict.A.max_length.getLongName());
            sequence = new Sequence(sequenceBuilder.getInitValue(), sequenceBuilder.getCurrentValue(), sequenceBuilder.getCacheSize(), sequenceBuilder.getSeqLength());

            //容灾机制(shutDownHook未触发)
            Long redisCurrentValue = (Long) RedisHelper.getValue(seqCode);
            if(CommUtil.isNotNull(redisCurrentValue) && redisCurrentValue > sequence.getCurrentValue()){
                log.info("Based on the latest serial number in the redis cache, the serial value: {}", redisCurrentValue);
                sequence.setCurrentValue(redisCurrentValue);
                //更新至数据库
                sequenceBuilder.setCurrentValue(redisCurrentValue);
                sequenceBuilderMapper.updateByPrimaryKey(sequenceBuilder);
            }
        }
        //根据序列信息生成序列号
        StringBuffer sequenceBuffer = new StringBuffer(sequence.getMaxLength());
        Long currentValue = sequence.getCurrentValue();

        if(CommUtil.isNull(sequence.getCurrentValue())){
            currentValue = Long.valueOf(sequence.getInitValue()) + gap;
        }else{
            currentValue += gap;
            if(String.valueOf(currentValue).length() > sequence.getMaxLength()){
                currentValue = Long.valueOf(sequence.getInitValue()) + gap;
            }
        }
        sequenceBuffer.append(zeroLeftPadding(sequence.getMaxLength() - String.valueOf(currentValue).length())).append(currentValue);
        sequence.setCurrentValue(currentValue);
        sequence.setCachedSize(sequence.getCachedSize() - gap);
        sequenceMap.put(seqCode, sequence);
        //存入redis
        RedisHelper.addAndSetValue(seqCode, currentValue, SdtConst.REDIS_SEQVALUE_TIMEOUT);

        //同步缓存序列号至数据库
        if(sequence.getCachedSize() == 0L){
            syncCachedSequence();
        }
        return sequenceBuffer.toString();
    }

    /**
     * @Description 同步缓存中的序列号信息至数据库
     * @Author sunshaoyu
     * @Date 2020/9/20-20:34
     */
    public synchronized static void syncCachedSequence(){
        sequenceMap.forEach((seqCode, sequence) -> {
            SdpSequenceBuilder sequenceBuilder = sequenceBuilderMapper.selectByPrimaryKey(seqCode, true);
            sequenceBuilder.setCurrentValue(sequence.getCurrentValue());
            sequenceBuilderMapper.updateByPrimaryKey(sequenceBuilder);
        });
        log.info("Synchronize the cache serial number to the database");
    }

    /**
     * @Description 左边补零
     * @Author sunshaoyu
     * @Date 2020/9/20-19:56
     * @param digit
     * @return java.lang.String
     */
    private static String zeroLeftPadding(int digit){
        StringBuilder builder = new StringBuilder(digit);
        while(digit-- > 0){
            builder.append("0");
        }
        return builder.toString();
    }
}
