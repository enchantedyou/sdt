package com.ssy.api.demo;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2021年05月14日-16:01
 */
public class BaseController {

	JdbcTemplate jdbcTemplate;

	public void preHandle() {
	}

	public void handle() {
		final Map<String, Object> resultMap = jdbcTemplate.queryForMap("select 1 from table");
		if (resultMap.isEmpty()) {
			throw new AppException(E_RESPONSE_CODE.INNER_ERROR.getValue(), "查询无结果");
		}
	}

	public void postHandle() {
	}

	public void validate(String port) {
		if (null == port || !port.contains(".")) {
			throw new AppException("端口号不合法");
		}
	}

	enum E_RESPONSE_CODE {
		SUCCESS(1, "响应成功"),
		INNER_ERROR(2, "系统内部错误"),
		OUTER_ERROR(3, "外部厂家错误");

		private Integer value;
		private String desc;

		E_RESPONSE_CODE(Integer value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public Integer getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}
}
