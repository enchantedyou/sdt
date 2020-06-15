package com.ssy.api.plugins;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.utils.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description 动态数据源
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:56
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DBContextHolder.getCurrentDataSource();
        if (CommUtil.isNull(datasource)) {
            datasource = SdtConst.MASTER_DATASOURCE;

        }
        log.debug("Determine current data source [{}]", datasource);
        return datasource;
    }
}
