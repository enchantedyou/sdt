package com.ssy.api.plugins;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_DATASOURCETYPE;
import com.ssy.api.entity.session.UserInfo;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description 数据源上下文处理器,用于操作动态数据源
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:57
 */
@Slf4j
public class DBContextHolder extends AbstractRoutingDataSource {

    /** 存储当前线程的数据源,如果是主数据源,则不保存 **/
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();
    /** 数据源类型(默认本地) **/
    private static final ThreadLocal<E_DATASOURCETYPE> dataSourceTypeLocal = new ThreadLocal<>();

    /**
     * @Description 切换动态数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:08
     * @param dataSource
     */
    public static void switchToDataSource(String dataSource) {
        if (OdbFactory.getDataSourceMap().containsKey(dataSource)) {
            if(!CommUtil.equals(dataSource, SdtConst.MASTER_DATASOURCE)){
                dataSourceTypeLocal.set(E_DATASOURCETYPE.REMOTE);
                dataSourceHolder.set(dataSource);
                log.info("The current data source is switched to [{}]", dataSource);
            }
        } else {
            ApPubErr.E0009(dataSource);
        }
    }

    /**
     * @Description 强制切换数据源
     * @Author sunshaoyu
     * @Date 2020/7/17-17:05
     * @param dataSource
     */
    public static void switchToDataSourceForce(String dataSource) {
        switchToDataSource(dataSource);
        UserInfo userInfo = SpringContextUtil.getSessionAttribute(SdtConst.USER_INFO, UserInfo.class);
        if(CommUtil.isNotNull(userInfo)){
            userInfo.setUserDataSource(dataSource);
        }
    }

    /**
     * @Description 获取当前数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-14:58
     * @return java.lang.String
     */
    public static String getCurrentDataSource() {
        return dataSourceHolder.get();
    }

    /**
     * @Description 删除当前数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-14:59
     */
    public static void clearCurrentDataSource() {
        dataSourceHolder.remove();
    }

    /**
     * @Description 决定当前数据源类型
     * @Author sunshaoyu
     * @Date 2020/7/20-13:12
     * @param dataSourceType
     */
    public static void determineCurrentDataSourceType(E_DATASOURCETYPE dataSourceType){
        dataSourceTypeLocal.set(dataSourceType);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource;
        if(dataSourceTypeLocal.get() == E_DATASOURCETYPE.REMOTE){
            datasource = getCurrentDataSource();
        }else{
            datasource = SdtConst.MASTER_DATASOURCE;
        }
        log.debug("Determine current data source {{}}", datasource);
        return datasource;
    }

    @Override
    public int hashCode() {
        return determineTargetDataSource().hashCode();
    }
}
