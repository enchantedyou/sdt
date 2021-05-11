package com.ssy.api.utils.http;

import java.io.*;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ssy.api.entity.lang.Params;
import com.ssy.api.utils.system.CommUtil;

/**
 * @Description HTTP工具类
 * @Author sunshaoyu
 * @Date 2020/7/29-9:49
 */
public class HttpUtil {

	private static final ThreadLocal<String> CHARSET_LOCAL = new ThreadLocal<>();

	/**
	 * @Description get请求,返回响应字符串
	 * @Author sunshaoyu
	 * @Date 2020/7/31-13:40
	 * @param url
	 * @return java.lang.String
	 */
	public static String doGet(String url) throws IOException {
		return doGet(url, null);
	}

	/**
	 * @Description get请求,返回响应字符串
	 * @Author sunshaoyu
	 * @Date 2020/7/29-10:07
	 * @param url
	 * @return java.lang.String
	 */
	public static String doGet(String url, Params hearders) throws IOException {
		return resolveResponse(doGet(url, "", hearders, null));
	}

	/**
	 * @Description post请求
	 * @Author sunshaoyu
	 * @Date 2020/8/12-14:51
	 * @param url
	 * @param hearders
	 * @param body
	 * @return java.lang.String
	 */
	public static String doPost(String url, Params hearders, String body) throws IOException {
		return resolveResponse(doPost(url, "", hearders, null, body));
	}

	/**
	 * @Description get请求
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:53
	 * @param host
	 * @param path
	 * @param headers
	 * @param querys
	 * @return org.apache.http.HttpResponse
	 */
	public static HttpResponse doGet(String host, String path, Map<String, Object> headers, Map<String, Object> querys)
			throws IOException {
		try {
			HttpClient httpClient = wrapClient(host);
			HttpGet request = new HttpGet(buildUrl(host, path, querys));
			appendHeaders(headers, request);
			return httpClient.execute(request);
		} finally {
			CHARSET_LOCAL.remove();
		}
	}

	/**
	 * @Description 为请求添加请求头
	 * @Author sunshaoyu
	 * @Date 2020/7/29-10:08
	 * @param headers
	 * @param request
	 */
	private static void appendHeaders(Map<String, Object> headers, HttpRequestBase request) {
		if (CommUtil.isNotNull(headers)) {
			headers.entrySet().forEach(e -> {
				request.addHeader(e.getKey(), String.valueOf(e.getValue()));
			});
		}
	}

	/**
	 * @Description Post请求
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:52
	 * @param host
	 * @param path
	 * @param headers
	 * @param querys
	 * @param body
	 * @return org.apache.http.HttpResponse
	 */
	public static HttpResponse doPost(String host, String path, Map<String, Object> headers, Map<String, Object> querys,
			String body) throws IOException {
		try {
			HttpClient httpClient = wrapClient(host);
			HttpPost request = new HttpPost(buildUrl(host, path, querys));
			appendHeaders(headers, request);

			if (CommUtil.isNotNull(body)) {
				request.setEntity(new StringEntity(body, determineCharset()));
			}
			return httpClient.execute(request);
		} finally {
			CHARSET_LOCAL.remove();
		}
	}

	/**
	 * @Description put请求
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:55
	 * @param host
	 * @param path
	 * @param headers
	 * @param querys
	 * @param body
	 * @return org.apache.http.HttpResponse
	 */
	public static HttpResponse doPut(String host, String path, Map<String, Object> headers, Map<String, Object> querys,
			String body) throws IOException {
		try {
			HttpClient httpClient = wrapClient(host);
			HttpPut request = new HttpPut(buildUrl(host, path, querys));
			appendHeaders(headers, request);

			if (CommUtil.isNotNull(body)) {
				request.setEntity(new StringEntity(body, determineCharset()));
			}
			return httpClient.execute(request);
		} finally {
			CHARSET_LOCAL.remove();
		}
	}

	/**
	 * @Description delete请求
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:56
	 * @param host
	 * @param path
	 * @param headers
	 * @param querys
	 * @return org.apache.http.HttpResponse
	 */
	public static HttpResponse doDelete(String host, String path, Map<String, Object> headers,
			Map<String, Object> querys) throws IOException {
		try {
			HttpClient httpClient = wrapClient(host);
			HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
			return httpClient.execute(request);
		} finally {
			CHARSET_LOCAL.remove();
		}
	}

	/**
	 * @Description 构建请求url
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:52
	 * @param host
	 * @param path
	 * @param querys
	 * @return java.lang.String
	 */
	private static String buildUrl(String host, String path, Map<String, Object> querys)
			throws UnsupportedEncodingException {
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(host);
		if (CommUtil.isNotNull(path)) {
			sbUrl.append(path);
		}
		if (CommUtil.isNotNull(querys)) {
			StringBuilder sbQuery = new StringBuilder();
			for (Map.Entry<String, Object> query : querys.entrySet()) {
				if (0 < sbQuery.length()) {
					sbQuery.append("&");
				}
				if (CommUtil.isNull(query.getKey()) && CommUtil.isNotNull(query.getValue())) {
					sbQuery.append(query.getValue());
				}
				if (CommUtil.isNotNull(query.getKey())) {
					sbQuery.append(query.getKey());
					if (CommUtil.isNotNull(query.getValue())) {
						sbQuery.append("=");
						sbQuery.append(
								URLEncoder.encode(String.valueOf(query.getValue()), determineCharset()));
					}
				}
			}
			if (0 < sbQuery.length()) {
				sbUrl.append("?").append(sbQuery);
			}
		}
		return sbUrl.toString();
	}

	/**
	 * 
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月19日-上午10:00:49</li>
	 *         <li>功能说明：输入流转字符串</li>
	 *         </p>
	 * @param inputStream 输入流
	 * @return 返回字符串
	 */
	public static String convertStreamToString(InputStream inputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(inputStream, determineCharset()))) {
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
			return builder.toString();
		} finally {
			CHARSET_LOCAL.remove();
		}
	}

	/**
	 * @Description 解析响应体
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:59
	 * @param httpResponse
	 * @return java.lang.String
	 */
	public static String resolveResponse(HttpResponse httpResponse) throws IOException {
		String responseStr = new String();
		HttpEntity httpEntity = httpResponse.getEntity();
		if (null != httpEntity) {
			InputStream instream = httpEntity.getContent();
			responseStr = convertStreamToString(instream);
		}
		return responseStr;
	}

	/**
	 * @Description 包装请求客户端
	 * @Author sunshaoyu
	 * @Date 2020/7/29-9:59
	 * @param host
	 * @return org.apache.http.client.HttpClient
	 */
	private static HttpClient wrapClient(String host) {
		HttpClient httpClient = new DefaultHttpClient();
		if (host.startsWith("https://")) {
			sslClient(httpClient);
		}
		return httpClient;
	}

	/**
	 * @Description 包装ssl客户端
	 * @Author sunshaoyu
	 * @Date 2020/7/29-10:03
	 * @param httpClient
	 */
	private static void sslClient(HttpClient httpClient) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
						throws CertificateException {

				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry registry = ccm.getSchemeRegistry();
			registry.register(new Scheme("https", 443, ssf));
		} catch (KeyManagementException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Description 设置字符集
	 * @Author sunshaoyu
	 * @Date 2021/5/10-13:53
	 * @param charset
	 */
	public static void determineCharset(String charset) {
		CHARSET_LOCAL.set(Objects.requireNonNull(charset));
	}

	private static String determineCharset() {
		return CommUtil.nvl(CHARSET_LOCAL.get(), "UTF-8");
	}
}