package com.ssy.api.logic.local;

import com.ssy.api.dao.mapper.local.SdpDictPriortyMapper;
import com.ssy.api.dao.mapper.local.SdpEnumPriortyMapper;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.dict.SdtTable;
import com.ssy.api.entity.table.local.SdpDictPriorty;
import com.ssy.api.entity.table.local.SdpEnumPriorty;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.utils.BizUtil;
import com.ssy.api.utils.CommUtil;
import com.ssy.api.utils.SdtBusiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 元数据优先级处理包
 * @Author sunshaoyu
 * @Date 2020年06月12日-13:28
 */
@Component
public class SdMetaPriorty {

    private static SdpDictPriortyMapper sdpDictPriortyMapper;
    private static SdpEnumPriortyMapper sdpEnumPriortyMapper;

    @Autowired
    public void setSdpDictPriortyMapper(SdpDictPriortyMapper sdpDictPriortyMapper) {
        SdMetaPriorty.sdpDictPriortyMapper = sdpDictPriortyMapper;
    }

    @Autowired
    public void setSdpEnumPriortyMapper(SdpEnumPriortyMapper sdpEnumPriortyMapper) {
        SdMetaPriorty.sdpEnumPriortyMapper = sdpEnumPriortyMapper;
    }

    /**
     * @Description 查询有效的字典优先级列表
     * @Author sunshaoyu
     * @Date 2020/6/12-13:46
     * @return java.util.List<com.ssy.api.entity.db.local.SdpDictPriorty>
     */
    public static List<SdpDictPriorty> queryEffectDictPriortyList(){
        return sdpDictPriortyMapper.selectAll_odb1(true);
    }

    /**
     * @Description 查询有效的枚举优先级列表
     * @Author sunshaoyu
     * @Date 2020/6/12-13:48
     * @return java.util.List<com.ssy.api.entity.db.local.SdpEnumPriorty>
     */
    public static List<SdpEnumPriorty> queryEffectEnumPriortyList(){
        return sdpEnumPriortyMapper.selectAll_odb1(true);
    }

    /**
     * @Description 字典优先级检查主方法
     * @Author sunshaoyu
     * @Date 2020/6/12-16:55
     * @param sdpDictPriorty
     */
    public static void checkDictPriortyMain(SdpDictPriorty sdpDictPriorty){
        BizUtil.fieldNotNull(sdpDictPriorty.getDictType(), SdtDict.A.dict_type.getId(), SdtDict.A.dict_type.getLongName());
        BizUtil.fieldNotNull(sdpDictPriorty.getDictPriority(), SdtDict.A.dict_priority.getId(), SdtDict.A.dict_priority.getLongName());
        SdtBusiUtil.checkAmountNotNegate(new BigDecimal(sdpDictPriorty.getDictPriority()), SdtDict.A.dict_priority.getLongName());
    }

    /**
     * @Description 枚举优先级检查主方法
     * @Author sunshaoyu
     * @Date 2020/6/12-16:57
     * @param sdpEnumPriorty
     */
    public static void checkEnumPriortyMain(SdpEnumPriorty sdpEnumPriorty){
        BizUtil.fieldNotNull(sdpEnumPriorty.getEnumType(), SdtDict.A.enum_type.getId(), SdtDict.A.enum_type.getLongName());
        BizUtil.fieldNotNull(sdpEnumPriorty.getEnumPriority(), SdtDict.A.enum_priority.getId(), SdtDict.A.enum_priority.getLongName());
        SdtBusiUtil.checkAmountNotNegate(new BigDecimal(sdpEnumPriorty.getEnumPriority()), SdtDict.A.enum_priority.getLongName());
    }

    /**
     * @Description 维护字典优先级
     * @Author sunshaoyu
     * @Date 2020/6/12-13:48
     * @param sdpDictPriorty
     */
    public static void modifyDictPriorty(SdpDictPriorty sdpDictPriorty){
        SdpDictPriorty oldData = sdpDictPriortyMapper.selectByPrimaryKey(sdpDictPriorty.getDictType(), true);

        if(!CommUtil.equals(oldData.getDataVersion(), sdpDictPriorty.getDataVersion())){
            ApPubErr.E0005(SdtTable.A.sdp_dict_priority.getLongName());
        }else if(CommUtil.equals(BizUtil.auditOnUpdate(oldData, sdpDictPriorty), 0)){
            ApPubErr.E0004();
        }else{
            sdpDictPriortyMapper.updateByPrimaryKey(sdpDictPriorty);
        }
    }

    /**
     * @Description 维护枚举优先级
     * @Author sunshaoyu
     * @Date 2020/6/12-17:14
     * @param sdpEnumPriorty
     */
    public static void modifyEnumPriorty(SdpEnumPriorty sdpEnumPriorty){
        SdpEnumPriorty oldData = sdpEnumPriortyMapper.selectByPrimaryKey(sdpEnumPriorty.getEnumType(), true);
        SdtBusiUtil.checkExistenceFromTableQry(oldData, SdtTable.A.sdp_enum_priority.getLongName());

        if(!CommUtil.equals(oldData.getDataVersion(), sdpEnumPriorty.getDataVersion())){
            ApPubErr.E0005(SdtTable.A.sdp_enum_priority.getLongName());
        }else if(CommUtil.equals(BizUtil.auditOnUpdate(oldData, sdpEnumPriorty), 0)){
            ApPubErr.E0004();
        }else{
            sdpEnumPriortyMapper.updateByPrimaryKey(sdpEnumPriorty);
        }
    }

    /**
     * @Description 新增字典优先级
     * @Author sunshaoyu
     * @Date 2020/6/12-17:16
     * @param sdpDictPriorty
     */
    public static void addDictPriorty(SdpDictPriorty sdpDictPriorty) {
        SdpDictPriorty oldData = sdpDictPriortyMapper.selectByPrimaryKey(sdpDictPriorty.getDictType(), true);
        if(CommUtil.isNotNull(oldData)){
            ApPubErr.E0006(SdtTable.A.sdp_dict_priority.getLongName(), SdtBusiUtil.parseStrArrayToSingle(sdpDictPriorty.getDictType()));
        }
        sdpDictPriortyMapper.insert(sdpDictPriorty);
    }

    /**
     * @Description 新增枚举优先级
     * @Author sunshaoyu
     * @Date 2020/6/12-17:17
     * @param sdpEnumPriorty
     */
    public static void addEnumPriorty(SdpEnumPriorty sdpEnumPriorty) {
        SdpEnumPriorty oldData = sdpEnumPriortyMapper.selectByPrimaryKey(sdpEnumPriorty.getEnumType(), true);
        if(CommUtil.isNotNull(oldData)){
            ApPubErr.E0006(SdtTable.A.sdp_enum_priority.getLongName(), SdtBusiUtil.parseStrArrayToSingle(sdpEnumPriorty.getEnumType()));
        }
        sdpEnumPriortyMapper.insert(sdpEnumPriorty);
    }

    /**
     * @Description 获取字典优先级
     * @Author sunshaoyu
     * @Date 2020/6/13-0:53
     * @param supportMsInd
     * @return java.util.Map<java.lang.String,com.ssy.api.entity.table.local.SdpDictPriorty>(字典类型, 字典优先级实体)
     */
    public static Map<String, SdpDictPriorty> getDictPriortyMap(boolean supportMsInd) {
        Map<String, SdpDictPriorty> map = new HashMap<>();
        List<SdpDictPriorty> dictPriortyList = queryEffectDictPriortyList();

        if(CommUtil.isNotNull(dictPriortyList)){
            for(SdpDictPriorty s : dictPriortyList){
                map.put(s.getDictType(), s);
            }
        }

        //如果不支持ms优先,则移除ms的优先级
        if(!supportMsInd){
            map.remove("MsDict");
        }
        return map;
    }

    /**
     * @Description 获取枚举优先级
     * @Author sunshaoyu
     * @Date 2020/6/13-0:32
     * @param supportMsInd
     * @return java.util.Map<java.lang.String,com.ssy.api.entity.table.local.SdpEnumPriorty>(枚举类型, 枚举优先级实体)
     */
    public static Map<String, SdpEnumPriorty> getEnumPriortyMap(boolean supportMsInd) {
        Map<String, SdpEnumPriorty> map = new HashMap<>();
        List<SdpEnumPriorty> enumPriortyList = queryEffectEnumPriortyList();

        if(CommUtil.isNotNull(enumPriortyList)){
            for(SdpEnumPriorty s : enumPriortyList){
                map.put(s.getEnumType(), s);
            }
        }

        //如果不支持ms优先,则移除ms的优先级
        if(!supportMsInd){
            map.remove("MsEnumType");
        }
        return map;
    }
}
