package com.ssy.api.serviceimpl;

import com.ssy.api.entity.table.local.SdpDictPriorty;
import com.ssy.api.entity.table.local.SdpEnumPriorty;
import com.ssy.api.logic.local.SdMetaPriorty;
import com.ssy.api.servicetype.ModulePriortyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 优先级相关处理服务实现
 * @Author sunshaoyu
 * @Date 2020年06月11日-16:45
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ModulePriortyServiceImpl implements ModulePriortyService {

    @Override
    public List<SdpDictPriorty> queryEffectDictPriortyList() {
        return SdMetaPriorty.queryEffectDictPriortyList();
    }

    @Override
    public List<SdpEnumPriorty> queryEffectEnumPriortyList() {
        return SdMetaPriorty.queryEffectEnumPriortyList();
    }

    @Override
    public void modifyDictPriorty(SdpDictPriorty sdpDictPriorty) {
        SdMetaPriorty.checkDictPriortyMain(sdpDictPriorty);
        SdMetaPriorty.modifyDictPriorty(sdpDictPriorty);
    }

    @Override
    public void modifyEnumPriorty(SdpEnumPriorty sdpEnumPriorty) {
        SdMetaPriorty.checkEnumPriortyMain(sdpEnumPriorty);
        SdMetaPriorty.modifyEnumPriorty(sdpEnumPriorty);
    }

    @Override
    public void addDictPriorty(SdpDictPriorty sdpDictPriorty) {
        SdMetaPriorty.checkDictPriortyMain(sdpDictPriorty);
        SdMetaPriorty.addDictPriorty(sdpDictPriorty);
    }

    @Override
    public void addEnumPriorty(SdpEnumPriorty sdpEnumPriorty) {
        SdMetaPriorty.checkEnumPriortyMain(sdpEnumPriorty);
        SdMetaPriorty.addEnumPriorty(sdpEnumPriorty);
    }

    @Override
    public Map<String, SdpDictPriorty> getDictPriortyMap(boolean supportMsInd) {
        return SdMetaPriorty.getDictPriortyMap(supportMsInd);
    }

    @Override
    public Map<String, SdpEnumPriorty> getEnumPriortyMap(boolean supportMsInd) {
        return SdMetaPriorty.getEnumPriortyMap(supportMsInd);
    }
}
