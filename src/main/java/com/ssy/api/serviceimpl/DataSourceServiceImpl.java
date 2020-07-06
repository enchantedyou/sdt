package com.ssy.api.serviceimpl;

import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.logic.local.SdDynamicDs;
import com.ssy.api.servicetype.DataSourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 数据源相关服务实现
 * @Author sunshaoyu
 * @Date 2020年06月15日-15:32
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DataSourceServiceImpl implements DataSourceService {

    @Override
    public List<SdpDatasource> queryDataSourceList() {
        return SdDynamicDs.queryDataSourceList();
    }
}
