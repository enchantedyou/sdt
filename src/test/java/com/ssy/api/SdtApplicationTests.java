package com.ssy.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.factory.odb.MetaDataFactory;
import com.ssy.api.utils.parse.ExcelParser;
import com.ssy.api.utils.system.CommUtil;

import lombok.Data;
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
		final List<String> readAllLines = Files.readAllLines(Paths.get("C:\\Users\\admin\\Desktop\\access0709.log"),
				StandardCharsets.UTF_8);
		StringBuilder builder = new StringBuilder(160000);

		for (String line : readAllLines) {
			if (CommUtil.isNull(line)) {
				continue;
			}

			final String[] array = line.split(" ");
			// 请求url
			String url = array[6];
			if (!url.startsWith("/sdwlzlapp/")) {
				continue;
			}

			// 请求ip
			String ip = array[0];
			// 请求时间
			final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			final String dateStr = array[3].substring(1).replace("Jul", "07");
			String reqTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(format.parse(dateStr));
			// 响应码
			String resCode = array[8];
			// 引用
			String refer = array[10].substring(1, array[10].length() - 1);

			String sql = String.format(
					"INSERT INTO `nginx_log` (`ip`, `req_time`, `res_code`, `url`, `refer`) VALUES ('%s', '%s', '%s', '%s', '%s');",
					ip, reqTime, resCode, url, refer);
			builder.append(sql).append("\r\n");
		}
		Files.write(Paths.get("C:\\Users\\admin\\Desktop\\ng.sql"), builder.toString().getBytes("utf-8"));
	}

	@Test
	void test2() throws Exception {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String startTime = "2021-07-09 22:00:00";
		final String endTime = "2021-07-09 23:00:00";
		final int interval = 1;
		start.setTime(sdf.parse(startTime));
		end.setTime(sdf.parse(endTime));

		// 先删除以往的记录
		jdbcTemplate.execute("delete from nginx_log_analysis;");
		List<ConcurrentInfo> concurrentInfoList = new ArrayList<>();

		while (start.before(end)) {
			String s = sdf.format(start.getTime());
			start.add(Calendar.MINUTE, interval);
			String e = sdf.format(start.getTime());

			String sql = String.format(
					"SELECT count(*) req_count,res_code FROM nginx_log WHERE req_time > '%s' AND req_time <= '%s' GROUP BY res_code ORDER BY res_code;",
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
			final double qps = BigDecimal.valueOf(totalCount)
					.divide(BigDecimal.valueOf(interval * 60), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String analysisSql = String.format(
					"INSERT INTO `nginx_log_analysis` (`开始时间`, `结束时间`, `响应数200`, `响应数304`, 响应数404, `响应数499`, `响应数500`, `响应数502`, `响应数504`, `总请求数`,qps) VALUES ('%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s);",
					s, e, r200, r304, r404, r499, r500, r502, r504, totalCount, qps);
			jdbcTemplate.execute(analysisSql);
			System.out.println(String.format("%s至%s的并发量分析完成", s, e));
			concurrentInfoList.add(new ConcurrentInfo(s, e, r200, r304, r404, r499, r500, r502, r504, totalCount, qps));
		}
		// 按总请求量倒序
		Collections.sort(concurrentInfoList, (a, b) -> {
			return b.totalCount - a.totalCount;
		});
		analysisInfoExcel(concurrentInfoList);
	}

	private void analysisInfoExcel(List<ConcurrentInfo> concurrentInfoList) throws IOException {
		String templatePath = "C:\\Users\\admin\\Desktop\\山东\\模板\\并发量分析.xlsx";
		final Workbook workbook = ExcelParser.getWorkbook(templatePath);
		final Sheet sheet = workbook.getSheetAt(0);
		int startIndex = 1;
		for (ConcurrentInfo c : concurrentInfoList) {
			final Row row = sheet.createRow(startIndex++);
			row.createCell(0).setCellValue(c.s);
			row.createCell(1).setCellValue(c.e);
			row.createCell(2).setCellValue(c.totalCount);
			row.createCell(3).setCellValue(c.qps);
			row.createCell(4).setCellValue(c.r200);
			row.createCell(5).setCellValue(c.r304);
			row.createCell(6).setCellValue(c.r404);
			row.createCell(7).setCellValue(c.r499);
			row.createCell(8).setCellValue(c.r500);
			row.createCell(9).setCellValue(c.r502);
			row.createCell(9).setCellValue(c.r504);
		}
		workbook.write(new FileOutputStream("C:\\Users\\admin\\Desktop\\0709并发分析（22-23时1分钟维度）.xlsx"));
	}

	@Data
	class ConcurrentInfo {
		private String s;
		private String e;
		private String r200;
		private String r304;

		private String r404;
		private String r499;
		private String r500;
		private String r502;
		private String r504;

		private int totalCount;
		private double qps;

		public ConcurrentInfo(String s, String e, String r200, String r304, String r404, String r499, String r500,
				String r502, String r504, int totalCount, double qps) {
			this.s = s;
			this.e = e;
			this.r200 = r200;
			this.r304 = r304;
			this.r404 = r404;
			this.r499 = r499;
			this.r500 = r500;
			this.r502 = r502;
			this.r504 = r504;
			this.totalCount = totalCount;
			this.qps = qps;
		}
	}
}
