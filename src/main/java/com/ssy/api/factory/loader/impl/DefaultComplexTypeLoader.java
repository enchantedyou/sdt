package com.ssy.api.factory.loader.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.local.SdpDictPriorty;
import com.ssy.api.exception.ApPubErr;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.ComplexTypeLoader;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.CommUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 默认复合类型加载器
 * @Author sunshaoyu
 * @Date 2020年06月11日-09:46
 */
@Component
@Slf4j
public class DefaultComplexTypeLoader implements ComplexTypeLoader {

	@Autowired
	private SdtContextConfig sdtContextConfig;

	@Override
	public Map<String, Map<String, ComplexType>> load(Map<String, File> fileMap) {
		Map<String, Map<String, ComplexType>> map = new ConcurrentHashMap<>();

		for (Map.Entry<String, File> fileEntry : fileMap.entrySet()) {
			String fileName = fileEntry.getKey();
			if (fileName.contains(SdtConst.DICT_SUFFIX) || fileName.contains(SdtConst.COMPLEX_SUFFIX)) {
				try {
					// 读取复合类型文件模型
					Element rootNode = XmlParser.getXmlRootElement(fileMap.get(fileName));
					// 获取复合类型节点列表
					List<Element> complexTypeList = XmlParser.searchTargetAllXmlElement(rootNode,
							SdtConst.COMPLEX_TYPE_NODE_NAME);
					// 复合类型位置
					String location = rootNode.attributeValue("id");

					Map<String, ComplexType> complexTypeMap = new HashMap<>();
					for (Element e : complexTypeList) {
						String complexTypeNodeId = e.attributeValue("id");
						// 当前复合类型是否为字典模型
						boolean isDict = Boolean.parseBoolean(e.attributeValue("dict"));

						// 获取该复合类型节点下的所有子元素
						Map<String, com.ssy.api.meta.defaults.Element> currentElementMap = getComplexTypeElementMap(map,
								e, location, isDict);
						complexTypeMap.put(complexTypeNodeId, new ComplexType(
								location, complexTypeNodeId, e.attributeValue("longname"), isDict, currentElementMap));
					}
					map.put(location, complexTypeMap);
				} catch (Exception e) {
					throw new SdtException("Failed to load metadata model", e);
				}
			}
		}
		return map;
	}

	/**
	 * @Description 获取复合类型的子元素集
	 * @Author sunshaoyu
	 * @Date 2020/6/13-15:00
	 * @param complexTypeNode 复合类型节点
	 * @param location        复合类型位置
	 * @param isDict          是否为字典类型的复合类型
	 * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.Element>
	 */
	private Map<String, com.ssy.api.meta.defaults.Element> getComplexTypeElementMap(
			Map<String, Map<String, ComplexType>> cpxMap, Element complexTypeNode, String location, boolean isDict) {
		Map<String, com.ssy.api.meta.defaults.Element> map = new ConcurrentHashMap<>();
		List<Element> elementList = XmlParser.searchTargetAllXmlElement(complexTypeNode, SdtConst.ELEMENT_NODE_NAME);
		String complexTypeNodeId = complexTypeNode.attributeValue("id");

		// 处理复合类型单个元素
		for (Element e : elementList) {
			String elementId = e.attributeValue("id");
			// 字典模型时,需自动生成ref
			String ref = isDict
					? (new StringBuffer().append(location).append(".").append(complexTypeNodeId).append(".")
							.append(elementId).toString())
					: e.attributeValue("ref");
			com.ssy.api.meta.defaults.Element currentElement = new com.ssy.api.meta.defaults.Element(
					location, elementId, e.attributeValue("longname"),
					getElementRestrictionType(cpxMap, e.attributeValue("type")), e.attributeValue("desc"), ref);
			currentElement.setMulti(Boolean.valueOf(e.attributeValue("multi")));

			if (CommUtil.isNotNull(elementId)) {
				map.put(elementId, currentElement);
			}
		}
		return map;
	}

	/**
	 * @Description 获取复合类型中每个元素的限制类型
	 * @Author sunshaoyu
	 * @Date 2020/6/13-14:45
	 * @param type
	 * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
	 */
	private AbstractRestrictionType getElementRestrictionType(Map<String, Map<String, ComplexType>> cpxMap,
			String type) {
		if (CommUtil.isNull(type) || !type.contains(".") || type.split("\\.").length < 2) {
			return null;
		} else {
			String[] arr = type.split("\\.");
			// 搜索限制类型
			AbstractRestrictionType restrictionType = OdbFactory.searchRestrictionType(arr[0], arr[1]);
			// 未搜索到限制类型,则可能是复合类型

			if (CommUtil.isNull(restrictionType)) {
				Map<String, ComplexType> map = cpxMap.get(arr[0]);
				if (CommUtil.isNotNull(map)) {
					return map.get(arr[1]);
				} else {
					// 延迟加载
					return new ComplexType(arr[0], arr[1], null, false, new HashMap<>());
				}
			} else {
				return restrictionType;
			}
		}
	}

	/**
	 * @Description 字典优先级检查
	 * @Author sunshaoyu
	 * @Date 2020/6/13-14:57
	 * @param before
	 * @param now
	 * @return com.ssy.api.meta.defaults.Element
	 */
	public com.ssy.api.meta.defaults.Element checkDictPriorty(Map<String, SdpDictPriorty> priority,
			com.ssy.api.meta.defaults.Element before, com.ssy.api.meta.defaults.Element now) {
		// 之前的数据为空或[当前或之前是微服务模型但不是微服务模型优先],直接添加
		if (CommUtil.isNull(before)
				|| ((SdtBusiUtil.isMsModel(now.getLocation()) || SdtBusiUtil.isMsModel(before.getLocation()))
						&& !sdtContextConfig.getMsModelFirst())) {
			return now;
		} else {
			// 获取两者的优先级
			SdpDictPriorty beforeDictPriorty = priority.get(before.getLocation());
			SdpDictPriorty nowDictPriorty = priority.get(now.getLocation());

			// 检查元数据模型的优先级是否存在
			if (CommUtil.isNull(beforeDictPriorty)) {
				ApPubErr.E0008(before.getLocation());
			} else if (CommUtil.isNull(nowDictPriorty)) {
				ApPubErr.E0008(now.getLocation());
			}

			// 两者都不含通配符且两者的组号不一致,不处理
			if (!beforeDictPriorty.getGroupId().contains(SdtConst.WILDCARD)
					&& !nowDictPriorty.getGroupId().contains(SdtConst.WILDCARD)
					&& !CommUtil.equals(beforeDictPriorty.getGroupId(), nowDictPriorty.getGroupId())) {
				return now;
			}
			// 返回优先级较高的一方
			else {
				if (CommUtil.compare(beforeDictPriorty.getDictPriority(), nowDictPriorty.getDictPriority()) < 0) {
					log.info("Dict type [{}] has lower priority than [{}] and should be removed", now.getRef(),
							before.getRef());
					return before;
				} else {
					log.info("Dict type [{}] has lower priority than [{}] and should be removed", before.getRef(),
							now.getRef());
					return now;
				}
			}
		}
	}
}
