package com.ssy.api.aop;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.github.pagehelper.PageInfo;
import com.ssy.api.dao.mapper.local.SdsPacketMapper;
import com.ssy.api.entity.annotation.TrxnEvent;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.lang.ResponseData;
import com.ssy.api.entity.table.local.SdsPacket;
import com.ssy.api.utils.http.HttpServletUtil;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.SpringContextUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 控制层增强
 * @Author sunshaoyu
 * @Date 2020年07月13日-13:37
 */
@RestControllerAdvice
@Aspect
@Slf4j
public class ControllerAspect implements ResponseBodyAdvice<Object> {

	/**
	 * 当前请求的交易事件
	 **/
	private static final ThreadLocal<String> trxnEventLocal = new ThreadLocal<>();

	@Autowired
	private SdsPacketMapper packetMapper;

	/**
	 * @param throwable
	 * @return com.ssy.api.entity.lang.ResponseData
	 * @Description 全局异常处理
	 * @Author sunshaoyu
	 * @Date 2020/7/15-11:24
	 */
	@ExceptionHandler
	public ResponseData exceptionHandler(Throwable throwable, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BizUtil.logError(throwable);
		ResponseData responseData = new ResponseData(throwable);
		registerResponsePacket(responseData, request, throwable);
		trxnEventLocal.remove();

		if (CommUtil.equals(request.getMethod(), SdtConst.GET_REQUEST)) {
			// 转发到错误页面
			request.setAttribute(SdtConst.RESPONSE_DATA, responseData);
			request.getRequestDispatcher("/exception").forward(request, response);
		}
		return responseData;
	}

	/**
	 * @param point
	 * @return java.lang.Object
	 * @Description 日志环绕增强
	 * @Author sunshaoyu
	 * @Date 2020/7/14-16:36
	 */
	@Around(value = "execution(* com.ssy.api.controller.*.*(..))")
	public Object controllerLogAdvice(ProceedingJoinPoint point) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		Object[] args = point.getArgs();

		StringBuilder builder = new StringBuilder();
		for (Object argument : args) {
			builder.append(argument).append(";");
		}

		Object responseData = null;
		String invokeMethodInfo = String.format("%s.%s", method.getDeclaringClass().getName(), method.getName());
		try {
			if (CommUtil.isNull(args)) {
				log.info("调用{}开始", invokeMethodInfo);
			} else {
				log.info("调用{}开始,请求参数:{}", invokeMethodInfo, builder.toString());
			}

			stopWatch.start();
			responseData = point.proceed(args);
		} finally {
			stopWatch.stop();
			if (CommUtil.isNull(responseData)) {
				log.info("调用{}结束,耗时:{}ms", invokeMethodInfo, stopWatch.getTotalTimeMillis());
			} else {
				log.info("调用{}结束,耗时:{}ms,返回结果:[{}]", invokeMethodInfo, stopWatch.getTotalTimeMillis(), responseData);
			}
		}
		return responseData;
	}

	/**
	 * @param methodParameter
	 * @param aClass
	 * @return boolean
	 * @Description 判断控制层方式是否支持响应封装
	 * @Author sunshaoyu
	 * @Date 2020/7/13-13:56
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		TrxnEvent trxnEventAnnotation = methodParameter.getMethodAnnotation(TrxnEvent.class);
		if (methodParameter.hasMethodAnnotation(TrxnEvent.class) && trxnEventAnnotation != null) {
			trxnEventLocal.set(trxnEventAnnotation.value());
			return true;
		}
		return false;
	}

	/**
	 * @param o
	 * @param methodParameter
	 * @param mediaType
	 * @param aClass
	 * @param serverHttpRequest
	 * @param serverHttpResponse
	 * @return java.lang.Object
	 * @Description 响应封装
	 * @Author sunshaoyu
	 * @Date 2020/7/13-14:12
	 */
	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
			ServerHttpResponse serverHttpResponse) {
		ResponseData responseData = null;
		if (CommUtil.isNull(o)) {
			responseData = new ResponseData();
		} else {
			if (o instanceof PageInfo) {
				// 赋值总数量
				BizUtil.getRunEnvs().setTotalCount(PageInfo.class.cast(o).getTotal());
			}
			responseData = new ResponseData(o);
		}
		registerResponsePacket(responseData, SpringContextUtil.getRequest());
		return responseData;
	}

	/**
	 * @param responseData
	 * @param request
	 * @param e
	 * @Description 登记请求响应信息
	 * @Author sunshaoyu
	 * @Date 2020/7/13-14:44
	 */
	private void registerResponsePacket(ResponseData responseData, HttpServletRequest request, Throwable e) {
		// 交易事件为空则不登记
		if (CommUtil.isNull(trxnEventLocal.get())) {
			return;
		}

		SdsPacket packet = new SdsPacket();
		packet.setTrxnSeq(BizUtil.getRunEnvs().getTrxnSeq());
		packet.setTrxnDate(BizUtil.getCurSysDate());
		packet.setTrxnDesc(trxnEventLocal.get());
		packet.setRequest(BizUtil.getRunEnvs().getRequestParams());

		packet.setBeginTime(BizUtil.getRunEnvs().getRequestStartTime());
		packet.setEndTime(responseData.getCommRes().getResponseTime());
		packet.setUsedTime(BizUtil.calTimeConsume(packet.getBeginTime(), packet.getEndTime()));
		packet.setHostIp(HttpServletUtil.getRemoteHostAddr(request));

		String errorText = responseData.getSys().getErortx();
		if (CommUtil.isNotNull(errorText) && errorText.length() <= 1000) {
			packet.setErrorText(errorText);
		}

		if (CommUtil.isNotNull(e)) {
			Throwable cause = getRootCause(e);
			StringBuilder builder = new StringBuilder();

			for (StackTraceElement traceElement : cause.getStackTrace()) {
				builder.append(String.format("%s.%s(%s:%d)", traceElement.getClassName(), traceElement.getMethodName(),
						traceElement.getFileName(), traceElement.getLineNumber())).append("\r\n");
			}
			packet.setErrorStack(builder.toString());
		}
		packetMapper.insert(packet);
	}

	/**
	 * @param responseData
	 * @param request
	 * @Description 登记请求响应信息
	 * @Author sunshaoyu
	 * @Date 2020/7/13-14:44
	 */
	private void registerResponsePacket(ResponseData responseData, HttpServletRequest request) {
		registerResponsePacket(responseData, request, null);
	}

	/**
	 * @param e
	 * @return java.lang.Throwable
	 * @Description 获取异常类中最根本的cause
	 * @Author sunshaoyu
	 * @Date 2020/7/14-14:20
	 */
	public Throwable getRootCause(Throwable e) {
		while (CommUtil.isNotNull(e.getCause())) {
			e = e.getCause();
		}
		return e;
	}
}
