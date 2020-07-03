package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.ap.AppDateMapper;
import com.ssy.api.dao.mapper.msap.MspOrganizationMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_DATECYCLE;
import com.ssy.api.entity.table.ap.AppDate;
import com.ssy.api.entity.table.msap.MspOrganization;
import com.ssy.api.servicetype.AppDateService;
import com.ssy.api.utils.BizUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 交易日期相关服务实现
 * @Author sunshaoyu
 * @Date 2020年07月02日-14:02
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AppDateServiceImpl implements AppDateService {

    @Autowired
    private MspOrganizationMapper mspOrganizationMapper;
    @Autowired
    private AppDateMapper appDateMapper;
    @Autowired
    private SdtContextConfig sdtContextConfig;

    private AppDate queryAppDate() {
        MspOrganization mspOrganization = mspOrganizationMapper.selectFirst_odb1();
        return appDateMapper.selectByPrimaryKey(mspOrganization.getBusiOrgId(), true);
    }

    @Override
    public String queryCurrentDate() {
        return queryAppDate().getTrxnDate();
    }

    @Override
    public void resetCurrentDate(String trxnDate) {
        BizUtil.fieldNotNull(trxnDate, SdtDict.A.trxn_date.getId(), SdtDict.A.trxn_date.getLongName());
        AppDate appDate = queryAppDate();

        appDate.setTrxnDate(trxnDate);
        appDate.setLastDate(BizUtil.dataAdd(E_DATECYCLE.DAY, trxnDate, -1));
        appDate.setNextDate(BizUtil.dataAdd(E_DATECYCLE.DAY, trxnDate, 1));
        appDateMapper.updateByPrimaryKey(appDate);
    }

    @Override
    public void resetCurrentDate() {
        resetCurrentDate(sdtContextConfig.getDefaultTrxnDate());
    }
}
