package com.ssy.api.demo;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2021年05月14日-16:17
 */
public class AppException extends RuntimeException {

	private int success;
	private String result;

	public AppException(int success, String result) {
		super(result);
		this.success = success;
		this.result = result;
	}

	public AppException(String result) {
		super(result);
		this.success = 5;
		this.result = result;
	}
}
