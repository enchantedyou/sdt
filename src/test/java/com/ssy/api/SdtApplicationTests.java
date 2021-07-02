package com.ssy.api;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.utils.system.CommUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = SdtApplication.class)
@Slf4j
class SdtApplicationTests extends MetaDataFactory {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	static {
		System.setProperty("jasypt.encryptor.password", SdtConst.CONFIG_ENCKEY);
	}

	@Test
	void contextLoads() throws Throwable {
		final List<String> readAllLines = Files.readAllLines(Paths.get("C:\\Users\\admin\\Desktop\\ng.log"),
				StandardCharsets.UTF_8);
		int i = 1;
		List<String> sqlList = new ArrayList<>();
		StringBuilder builder = new StringBuilder(160000);

		for (String line : readAllLines) {
			if (CommUtil.isNull(line)) {
				continue;
			}

			final String[] array = line.split(" ");
			// 请求url
			String url = array[6];
			if (!url.startsWith("/sdwlzlapp")) {
				continue;
			}

			// 请求ip
			String ip = array[0];
			// 请求时间
			final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			final String dateStr = array[3].substring(1).replace("Jun", "06");
			String reqTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(format.parse(dateStr));
			// 响应码
			String resCode = array[8];
			// 引用
			String refer = array[10].substring(1, array[10].length() - 1);

			String sql = String.format(
					"INSERT INTO `nginx_log` (`ip`, `req_time`, `res_code`, `url`, `refer`) VALUES ('%s', '%s', '%s', '%s', '%s');",
					ip, reqTime, resCode, url, refer);
			// sqlList.add(sql);
			builder.append(sql).append("\r\n");
			if (sqlList.size() == 100) {
				// jdbcTemplate.batchUpdate(sqlList.toArray(new String[100]));
				sqlList.clear();
				System.out.println(String.format("第%s批日志入库成功", i++));
			}
		}
		Files.write(Paths.get("C:\\Users\\admin\\Desktop\\ng.sql"), builder.toString().getBytes("utf-8"));
	}

	@Test
	void test2() throws Exception {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String startTime = "2021-06-30 16:00:00";
		final String endTime = "2021-06-30 19:00:00";
		final int interval = 5;
		start.setTime(sdf.parse(startTime));
		end.setTime(sdf.parse(endTime));

		while (start.before(end)) {
			String s = sdf.format(start.getTime());
			start.add(Calendar.MINUTE, interval);
			String e = sdf.format(start.getTime());

			String sql = String.format(
					"SELECT count(*) req_count,res_code FROM nginx_log WHERE req_time >= '%s' AND req_time <= '%s' GROUP BY res_code ORDER BY res_code;",
					s, e);

			final List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
			String totalCountSql = String
					.format("SELECT count(*) 总数 FROM nginx_log WHERE req_time >= '%s' AND req_time <= '%s'", s, e);
			int totalCount = jdbcTemplate.queryForObject(totalCountSql, Integer.class);

			String r200 = null;
			String r304 = null;
			String r404 = null;
			String r499 = null;
			String r500 = null;
			String r502 = null;
			String r504 = null;
			for (Map<String, Object> map : mapList) {
				final Object resCode = map.get("res_code");
				final Object reqCount = map.get("req_count");

				if ("200".equals(resCode)) {
					r200 = String.valueOf(reqCount);
				} else if ("304".equals(resCode)) {
					r304 = String.valueOf(reqCount);
				} else if ("404".equals(resCode)) {
					r404 = String.valueOf(reqCount);
				} else if ("499".equals(resCode)) {
					r499 = String.valueOf(reqCount);
				} else if ("500".equals(resCode)) {
					r500 = String.valueOf(reqCount);
				} else if ("502".equals(resCode)) {
					r502 = String.valueOf(reqCount);
				} else if ("504".equals(resCode)) {
					r504 = String.valueOf(reqCount);
				} else {
					System.err.println(resCode);
				}
			}
			String analysisSql = String.format(
					"INSERT INTO `nginx_log_analysis` (`开始时间`, `结束时间`, `响应数200`, `响应数304`, 响应数404, `响应数499`, `响应数500`, `响应数502`, `响应数504`, `总请求数`) VALUES ('%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s);",
					s, e, r200, r304, r404, r499, r500, r502, r504, totalCount);
			jdbcTemplate.execute(analysisSql);
			System.out.println(String.format("%s至%s的请求数量分析完成", s, e));
		}
	}
}
