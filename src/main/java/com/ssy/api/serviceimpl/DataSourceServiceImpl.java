package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.system.ProcessMapper;
import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.entity.type.local.SdDatasourceEdit;
import com.ssy.api.logic.local.SdDynamicDs;
import com.ssy.api.servicetype.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private ProcessMapper processMapper;

    @Override
    public List<SdpDatasource> queryDataSourceList() {
        return SdDynamicDs.queryDataSourceList();
    }

    @Override
    public void editDataSource(SdDatasourceEdit datasourceEdit) {
        SdDynamicDs.editDataSource(datasourceEdit);
    }
}
