package com.ssy.api.plugins;

import com.ssy.api.exception.ApPubErr;
import com.ssy.api.utils.DataSourceUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 数据源上下文处理器,用于操作动态数据源
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:57
 */
@Slf4j
public class DBContextHolder {

    /** 存储当前线程的数据源 **/
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * @Description 切换动态数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:08
     * @param dataSource
     */
    public static void switchToDataSource(String dataSource) {
        if (DataSourceUtil.dataSourceMap.containsKey(dataSource)) {
            log.debug("The current data source is switched to [{}]", dataSource);
            contextHolder.set(dataSource);
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
    public static String getCurrentDataSource() {
        return contextHolder.get();
    }

    /**
     * @Description 删除当前数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-14:59
     */
    public static void clearCurrentDataSource() {
        contextHolder.remove();
    }
}
