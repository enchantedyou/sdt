package com.ssy.api.logic.local;

import com.ssy.api.dao.mapper.local.SdpDatasourceMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.dict.SdtTable;
import com.ssy.api.entity.enums.E_DATAOPERATE;
import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.entity.type.local.SdDatasourceEdit;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.servicetype.SystemParamService;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.security.AesEnDecrypt;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 动态数据源相关处理包
 * @Author sunshaoyu
 * @Date 2020年06月15日-15:39
 */
@Component
@Slf4j
public class SdDynamicDs {

	private static SdpDatasourceMapper sdpDatasourceMapper;
	private static SystemParamService systemParamService;
	private static final String TLSQL_IND = "N";
	private static final String PLATFORM_TABLE_PREFIX = "MS";
	private static final String DATASOURCE_DRIVER = "com.mysql.cj.jdbc.Driver";

	@Autowired
	public void setSdpDatasourceMapper(SdpDatasourceMapper sdpDatasourceMapper) {
		SdDynamicDs.sdpDatasourceMapper = sdpDatasourceMapper;
	}

	@Autowired
	public void setSystemParamService(SystemParamService systemParamService) {
		SdDynamicDs.systemParamService = systemParamService;
	}

	/**
	 * @Description 查询数据源列表
	 * @Author sunshaoyu
	 * @Date 2020/6/15-15:44
	 * @return java.util.List<com.ssy.api.entity.table.local.SdpDatasource>
	 */
	public static List<SdpDatasource> queryDataSourceList() {
		List<SdpDatasource> datasourceList = sdpDatasourceMapper.selectAll();
		String aesEncKey = systemParamService.getValue(SdtConst.AES_ENC_KEY);

		// 数据源用户名密码脱敏处理
		if (SdtBusiUtil.isEnabledPagination()) {
			datasourceList.forEach(sdpDatasource -> {
				sdpDatasource.setDatasourceUser(AesEnDecrypt.aesEncrypt(sdpDatasource.getDatasourceUser(), aesEncKey));
				sdpDatasource.setDatasourcePwd(AesEnDecrypt.aesEncrypt(sdpDatasource.getDatasourcePwd(), aesEncKey));
			});
		}
		return datasourceList;
	}

	/**
	 * @Description 数据源维护
	 * @Author sunshaoyu
	 * @Date 2020/8/7-14:12
	 * @param datasourceEdit
	 */
	public static void editDataSource(SdDatasourceEdit datasourceEdit) {
		BizUtil.fieldNotNull(datasourceEdit.getDataOperateType(), SdtDict.A.data_operate_type.getId(),
				SdtDict.A.data_operate_type.getLongName());
		BizUtil.fieldNotNull(datasourceEdit.getDatasourceId(), SdtDict.A.datasource_id.getId(),
				SdtDict.A.datasource_id.getLongName());
		SdpDatasource dataSource = sdpDatasourceMapper.selectByPrimaryKey(datasourceEdit.getDatasourceId(),
				SdtConst.DATASOURCE_TYPE, false);

		if (datasourceEdit.getDataOperateType() == E_DATAOPERATE.A) {
			if (CommUtil.isNotNull(dataSource)) {
				throw ApPubErr.E0006(SdtTable.A.sdp_datasource.getLongName(), SdtBusiUtil
						.parseStrArrayToSingle(dataSource.getDatasourceId(), dataSource.getDatasourceType()));
			} else {
				dataSource = CommUtil.copyToTargetObject(datasourceEdit, SdpDatasource.class);
				defaultValueSet(dataSource);
				sdpDatasourceMapper.insert(dataSource);
			}
		} else if (datasourceEdit.getDataOperateType() == E_DATAOPERATE.M) {
			if (CommUtil.isNull(dataSource)) {
				throw SdtServError.E0003(SdtTable.A.sdp_datasource.getLongName(), datasourceEdit.getDatasourceId(),
						datasourceEdit.getDatasourceType());
			} else {
				SdpDatasource newDataSource = CommUtil.copyToTargetObject(datasourceEdit, SdpDatasource.class);
				defaultValueSet(dataSource);
				if (BizUtil.auditOnUpdate(dataSource, newDataSource) == 0) {
					throw ApPubErr.E0004();
				} else {
					sdpDatasourceMapper.updateByPrimaryKey(newDataSource);
				}
			}
		} else if (datasourceEdit.getDataOperateType() == E_DATAOPERATE.D) {
			if (CommUtil.isNull(dataSource)) {
				throw SdtServError.E0003(SdtTable.A.sdp_datasource.getLongName(), datasourceEdit.getDatasourceId(),
						datasourceEdit.getDatasourceType());
			} else {
				dataSource.setIsEnabled(false);
				sdpDatasourceMapper.updateByPrimaryKey(dataSource);
			}
		}
	}

	/**
	 * @Description 数据源默认值处理
	 * @Author sunshaoyu
	 * @Date 2020/8/7-14:02
	 * @param dataSource
	 */
	private static void defaultValueSet(SdpDatasource dataSource) {
		dataSource.setDatasourceType(CommUtil.nvl(dataSource.getDatasourceType(), SdtConst.DATASOURCE_TYPE));
		dataSource.setTlsqlInd(CommUtil.nvl(dataSource.getTlsqlInd(), TLSQL_IND));
		dataSource.setPlatformTablePrefix(CommUtil.nvl(dataSource.getPlatformTablePrefix(), PLATFORM_TABLE_PREFIX));
		dataSource.setDatasourceDriver(CommUtil.nvl(dataSource.getDatasourceDriver(), DATASOURCE_DRIVER));
	}
}
