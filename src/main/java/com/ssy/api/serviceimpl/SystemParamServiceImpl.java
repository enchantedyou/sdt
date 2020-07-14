package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.local.SdpSystemParameterMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.servicetype.SystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月05日-17:52
 */
@Service
public class SystemParamServiceImpl implements SystemParamService {

    @Autowired
    private SdpSystemParameterMapper systemParameterMapper;

    @Override
    public String getValue(String mainKey, String subKey) {
        return systemParameterMapper.selectByPrimaryKey(mainKey, subKey, true).getParmValue();
    }

    @Override
    public String getValue(String mainKey) {
        return getValue(mainKey, SdtConst.WILDCARD);
    }
}
