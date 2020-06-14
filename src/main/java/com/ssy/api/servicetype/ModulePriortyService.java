package com.ssy.api.servicetype;

import com.ssy.api.entity.table.local.SdpDictPriorty;
import com.ssy.api.entity.table.local.SdpEnumPriorty;

import java.util.List;
import java.util.Map;

/**
 * @Description 优先级相关处理服务接口
 * @Author sunshaoyu
 * @Date 2020/6/11-16:38
 */
public interface ModulePriortyService {

    public List<SdpDictPriorty> queryEffectDictPriortyList();

    public List<SdpEnumPriorty> queryEffectEnumPriortyList();

    public void modifyDictPriorty(SdpDictPriorty sdpDictPriorty);

    public void modifyEnumPriorty(SdpEnumPriorty sdpEnumPriorty);

    public void addDictPriorty(SdpDictPriorty sdpDictPriorty);

    public void addEnumPriorty(SdpEnumPriorty sdpEnumPriorty);

    public Map<String, SdpDictPriorty> getDictPriortyMap(boolean supportMsInd);

    public Map<String, SdpEnumPriorty> getEnumPriortyMap(boolean supportMsInd);
}
