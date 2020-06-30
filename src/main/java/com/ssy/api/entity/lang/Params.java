package com.ssy.api.entity.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文件功能说明： 对HashMap类的封装
 * </p>
 * 
 * @Author sunshaoyu
 *         <p>
 *         <li>2019年3月27日-下午3:56:36</li>
 *         <li>修改记录</li>
 *         <li>-----------------------------------------------------------</li>
 *         <li>标记：修订内容</li>
 *         <li>2019年3月27日-sunshaoyu：创建注释模板</li>
 *         <li>-----------------------------------------------------------</li>
 *         </p>
 */
public class Params extends HashMap<String, Object> {

	public Params() {
		super();
	}

	public Params(Map<String, Object> map) {
		putAll(map);
	}

	public Params add(String key, Object value) {
		put(key, value);
		return this;
	}

	public Params addAll(Map<String, Object> map) {
		putAll(map);
		return this;
	}

	public Params addAll(Params map) {
		putAll(map);
		return this;
	}

	public String toString() {
		return super.toString();
	}

	public Params del(String key) {
		remove(key);
		return this;
	}
}
