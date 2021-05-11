package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.SelectPageWithCount;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpDatasource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "sdp_datasource", desc = "parameter of dynamic data source")
public interface SdpDatasourceMapper {
	int deleteByPrimaryKey(@Param("datasourceId") String datasourceId, @Param("datasourceType") String datasourceType);

	int insert(SdpDatasource record);

	int insertSelective(SdpDatasource record);

	@EnableNotNull
	SdpDatasource selectByPrimaryKey(@Param("datasourceId") String datasourceId,
			@Param("datasourceType") String datasourceType, boolean nullException);

	int updateByPrimaryKeySelective(SdpDatasource record);

	int updateByPrimaryKey(SdpDatasource record);

	/** 查询数据源列表 **/
	@SelectPageWithCount
	List<SdpDatasource> selectAll();
}