package com.ssy.api;

import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.ssy.api.utils.parse.ExcelParser;
import com.ssy.api.utils.security.AesEnDecrypt;
import com.ssy.api.utils.system.CommUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description sdt单元测试demo
 * @Author sunshaoyu
 * @Date 2021年04月02日-13:14
 */
@Slf4j
public class SimpleTest {

	interface Performance {
		void execute() throws Throwable;

		String getFuncName();
	}

	private void calInterval(Performance... func) throws Throwable {
		for (int i = 0; i < func.length; i++) {
			long s = System.currentTimeMillis();
			try {
				func[i].execute();
			} finally {
				System.out.printf("功能[%s]耗时：%d ms\n", func[i].getFuncName(), (System.currentTimeMillis() - s));
			}
		}
	}

	@Test
	void test1() throws Throwable {
		String charset = "UTF-8";
		String encryptStr = new String(Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\1.txt")), charset);
		String param = AesEnDecrypt.aesDecrypt(encryptStr, "{MasterCom}16800", "4567812345678123");
		Map<String, Object> map = new LinkedHashMap<>();
		String[] paramEntries = param.split("&");
		for (int i = 0; i < paramEntries.length; i++) {
			String[] entry = paramEntries[i].split("=");
			map.put(entry[0], entry.length > 1 ? URLDecoder.decode(entry[1], charset) : "");
		}
		System.out.println(JSON.toJSONString(map));
	}

	@Test
	public void test2() throws Throwable {
		calInterval(new Performance() {

			@Override
			public void execute() throws Throwable {
				StringBuilder builder = new StringBuilder();
				String format = "INSERT INTO [tb_指标情况]([指标类型], [指标名称], [地市], [指标值], [时间], [基准值], [挑战值], [img_name], [interfaceName], [责任人], [入库时间], [统计周期]) VALUES (N'%s', N'%s', '%s', '%s', '%s', '%s', '%s', NULL, NULL, '%s', CONVERT(varchar,GETDATE(),120), '%s');";
				final String path = "C:\\Users\\admin\\Desktop\\网优平台关键指标新增指标字段信息.xlsx";
				final List<List<String>> dataList = ExcelParser.extract(path, 0);
				final List<String> titleList = dataList.get(0);

				for (int i = 1; i < dataList.size(); i++) {
					final List<String> rowList = dataList.get(i);
					if (CommUtil.isNull(rowList.get(2))) {
						continue;
					}

					for (int j = 5; j <= 22; j++) {
						final String row = String.format(format, rowList.get(0), rowList.get(1), titleList.get(j),
								rowList.get(j), "2021-05-01 00:00:00.000", "", "",
								rowList.get(2), rowList.get(4));
						builder.append(row).append("\r\n");
					}
				}

				builder.append("\r\n");
				format = "INSERT INTO [tb_keyIndex_config_indexDetial]([指标名称], [指标类型], [统计周期], [百分比], [责任人], [指标单位], [显示指标名称]) VALUES (N'%s', N'%s', N'%s', 1, N'%s', '百分号', '%s');";
				for (int i = 1; i < dataList.size(); i++) {
					final List<String> rowList = dataList.get(i);
					String s = rowList.get(4);
					if ("日".equals(s)) {
						s = "天";
					}

					String row = String.format(format, rowList.get(1), rowList.get(0), s,
							rowList.get(2), rowList.get(1));
					builder.append(row).append("\r\n");
				}
				final String s = builder.toString().replace("百分号", "%");
				Files.write(Paths.get("C:\\Users\\admin\\Desktop\\2858_掌上网优关键指标扩容.sql"),
						s.getBytes(StandardCharsets.UTF_8));
			}

			@Override
			public String getFuncName() {
				return "测试";
			}
		});
	}

	@Test
	public void test3() throws Exception {
		List<String> dataList = new ArrayList<>();
		String log = "117.136.94.74 - - [30/Jun/2021:23:24:58 +0800] \"POST /sdwlzlapp/wlmyd/GetServiceQuality.mt HTTP/1.1\" 200 16976 \"http://ganzhi.sd.chinamobile.com:8092/sdwlzlapp/web/overview.html?isNeedBackBtn=1&cityId=635&randomId=1625065760971\" \"Mozilla/5.0 (Linux; Android 10; NOH-AN01 Build/HUAWEINOH-AN01; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.106 Mobile Safari/537.36\"";
		final String[] array = log.split(" ");

		// 请求ip
		dataList.add(array[0]);
		// 请求时间
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
		final String dateStr = array[3].substring(1).replace("Jun", "06");
		dataList.add(String.valueOf(format.parse(dateStr).getTime()));
		// 请求url
		dataList.add(array[6]);
		// 响应码
		dataList.add(array[8]);
		// 引用
		dataList.add(array[10].substring(1, array[10].length() - 1));

		for (String s : dataList) {
			System.out.println(s);
		}
	}

	@Test
	public void test4() {
		System.out.println(Long.parseLong("30/Jun/2021:23:24:58 +0800"));
	}

	public static boolean isNull(Object obj) {
		if (null == obj) {
			return true;
		} else if (obj instanceof String) {
			String str = String.class.cast(obj);
			return str.trim().isEmpty() || str.equals("null");
		} else if (obj instanceof Map) {
			return Map.class.cast(obj).isEmpty();
		} else if (obj instanceof Collection) {
			return Collection.class.cast(obj).isEmpty();
		} else if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		} else if (obj instanceof Iterator) {
			return !Iterator.class.cast(obj).hasNext();
		}
		return false;
	}

	public int unique(int[] arr) {
		if (null == arr || arr.length == 0) {
			return 0;
		}

		// 排序后的快慢指针
		int i = 0;
		for (int j = 1; j < arr.length; j++) {
			if (arr[j] != arr[i]) {
				arr[++i] = arr[j];
			}
		}
		final int len = i + 1;
		for (int k = len; k < arr.length; k++) {
			arr[k] = 0;
		}
		return len;
	}

	public void sum(int[] arr, int target) {
		if (null == arr || arr.length == 0) {
			return;
		}

		// <值, 下标>
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			Integer index = map.get(target - arr[i]);
			if (index != null) {
				System.out.println(i + ":[" + arr[i] + "] + " + index + ":[" + arr[index] + "]");
			} else {
				map.put(arr[i], i);
			}
		}
	}

	public void q(int[] arr, int l, int h) {
		if (null == arr || arr.length == 0 || l > h) {
			return;
		}

		int key = arr[l];
		int i = l, j = h;
		while (i < j) {
			while (i < j && arr[j] > key) {
				j--;
			}

			while (i < j && arr[i] <= key) {
				i++;
			}

			if (i < j) {
				swap(arr, i, j);
			}
		}
		swap(arr, i, l);

		q(arr, l, i - 1);
		q(arr, i + 1, h);
	}

	public void s(int[] arr) {
		if (null == arr || arr.length == 0) {
			return;
		}

		for (int i = 1; i < arr.length; i++) {
			boolean flag = false;
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					flag = true;
				}
			}

			if (!flag) {
				break;
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

}
