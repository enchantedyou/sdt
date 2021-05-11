package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.local.SdpModuleMappingMapper;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 业务模块映射接口实现
 * @Author sunshaoyu
 * @Date 2020年07月31日-13:15
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ModuleMapServiceImpl implements ModuleMapService {

	@Autowired
	private SdpModuleMappingMapper moduleMappingMapper;

	@Override
	public SdpModuleMapping getModuleMapping(String moduleId) {
		BizUtil.fieldNotNull(moduleId, SdtDict.A.module_id.getId(), SdtDict.A.module_id.getLongName());
		return moduleMappingMapper.selectByPrimaryKey(moduleId, true);
	}

	@Override
	public List<SdpModuleMapping> queryMergeModuleList() {
		return moduleMappingMapper.selectMergeEnableList();
	}

	@Override
	public List<SdpModuleMapping> queryAllModuleList() {
		return moduleMappingMapper.selectAll();
	}

	@Override
	public String getServiceCode(String innerServiceCode) {
		BizUtil.fieldNotNull(innerServiceCode, SdtDict.A.inner_service_code.getId(),
				SdtDict.A.inner_service_code.getLongName());
		String moduleId = innerServiceCode.substring(0, 2);
		String serviceCode = innerServiceCode.substring(2);
		return getModuleMapping(moduleId).getServiceCodePrefix() + serviceCode;
	}

	@Override
	public String getModuleName(String subSystemCode) {
		List<SdpModuleMapping> moduleMappingList = queryAllModuleList();
		for (SdpModuleMapping moduleMapping : moduleMappingList) {
			if (CommUtil.equals(subSystemCode, moduleMapping.getSubSystemCode())) {
				return moduleMapping.getModuleId();
			}
		}
		return null;
	}

	@Override
	public String getInnerServiceCode(String outServiceCode) {
		List<SdpModuleMapping> moduleMappingList = queryAllModuleList();
		for (SdpModuleMapping moduleMapping : moduleMappingList) {
			if (CommUtil.equals(outServiceCode.substring(0, 2), moduleMapping.getServiceCodePrefix())) {
				return moduleMapping.getModuleId() + outServiceCode.substring(2);
			}
		}
		return null;
	}
}
