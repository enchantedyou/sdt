package com.ssy.api.plugins;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.utils.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description 数据源上下文处理器,用于操作动态数据源
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:57
 */
@Slf4j
public class DBContextHolder extends AbstractRoutingDataSource {

    /** 存储当前线程的数据源 **/
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    /** 存储当前的动态数据源(排除主数据源) **/
    //TO-DO 从session获取当前动态数据源
    private static final ThreadLocal<String> excptMasterHolder = new ThreadLocal<String>();

    /**
     * @Description 切换动态数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:08
     * @param dataSource
     */
    public synchronized static void switchToDataSource(String dataSource) throws Exception {
        if (OdbFactory.getDataSourceMap().containsKey(dataSource)) {
            log.debug("The current data source is switched to [{}]", dataSource);
            contextHolder.set(dataSource);

            if(!CommUtil.equals(dataSource, SdtConst.MASTER_DATASOURCE)){
                excptMasterHolder.set(dataSource);
            }
        } else {
            ApPubErr.E0009(dataSource);
        }
    }

    /**
     * @Description 获取
     * @Author sunshaoyu
     * @Date 2020/6/15-14:58
     * @return java.lang.String
     */
    public synchronized static String getCurrentDataSource() {
        return contextHolder.get();
    }

    /**
     * @Description 删除当前数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-14:59
     */
    public synchronized static void clearCurrentDataSource() {
        contextHolder.remove();
    }

    /**
     * @Description 获取当前非主数据源的动态数据源
     * @Author sunshaoyu
     * @Date 2020/7/3-14:38
     * @return java.lang.String
     */
    public synchronized static String getDynamicDataSource() {
        return excptMasterHolder.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = getCurrentDataSource();
        if (CommUtil.isNull(datasource)) {
            datasource = SdtConst.MASTER_DATASOURCE;
        }
        log.debug("Determine current data source {{}}", datasource);
        return datasource;
    }
}
