package com.ssy.api.factory.loader.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.FileLoader;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 默认文件加载器
 * @Author sunshaoyu
 * @Date 2020年05月28日-14:28
 */
@Component
@Slf4j
public class DefaultFileLoader implements FileLoader {

	@Autowired
	private SdtContextConfig sdtContextConfig;

	@Override
	public Map<String, File> load(String path, boolean isLimitJavaReources, String... suffix) {
		Map<String, File> map = new ConcurrentHashMap<>();
		try {
			File file = new File(path);
			loadFile(map, file, isLimitJavaReources, suffix);

			// 加载微服务模型
			if (isLimitJavaReources) {
				loadMsModel(map);
			}
		} catch (Exception e) {
			throw new SdtException("An unexpected exception occurred while loading the file", e);
		}
		return map;
	}

	/**
	 * @Description 递归加载文件
	 * @Author sunshaoyu
	 * @Date 2020/6/13-19:49
	 * @param map
	 * @param file
	 * @param isLimitJavaReources
	 * @param suffix
	 */
	private void loadFile(Map<String, File> map, File file, boolean isLimitJavaReources, String... suffix) {
		if (file.isDirectory()) {
			File[] subFileList = file.listFiles();
			for (File subFile : subFileList) {
				loadFile(map, subFile, isLimitJavaReources, suffix);
			}
		} else {
			String fileName = file.getName();
			// 排除非java资源目录
			if (isLimitJavaReources && !file.getPath().contains(SdtConst.JAVA_RESOURCES_PATH)) {
				return;
			}

			if (CommUtil.isNotNull(suffix)) {
				if (fileName.contains(".")) {
					for (String s : suffix) {
						if (s.equals(BizUtil.getFileType(fileName))) {
							map.put(fileName, file);
						}
					}
				}
			} else {
				map.put(fileName, file);
			}
		}
	}

	/**
	 * @Description 加载微服务模型
	 * @Author sunshaoyu
	 * @Date 2020/7/17-10:14
	 * @param map
	 */
	private void loadMsModel(Map<String, File> map) {
		File[] files = new File(DefaultFileLoader.class.getResource("/templates/msModel").getPath()).listFiles();
		for (File f : files) {
			map.put(f.getName(), f);
		}
	}

	public String loadAsString(File file, String charset) throws IOException {
		try (FileInputStream inputStream = new FileInputStream(file)) {
			byte[] buffer = new byte[inputStream.available()];
			if (inputStream.read(buffer) > 0) {
				return new String(buffer, charset);
			}
		}
		return null;
	}

	public List<String> loadLineAsList(File file, String charset) throws IOException {
		List<String> stringList = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), charset))) {
			String line = null;

			while (CommUtil.isNotNull((line = bufferedReader.readLine()))) {
				stringList.add(line);
			}
		}
		return stringList;
	}

	@Override
	public void mergeFile(String dir, File target, String charset) throws IOException {
		StringBuffer buffer = new StringBuffer();
		File[] files = new File(dir).listFiles();
		for (File file : files) {
			String content = loadAsString(file, charset);
			if (CommUtil.isNotNull(content)) {
				buffer.append(content).append("\n");
			}
		}
		saveFile(buffer.toString(), target.getPath());
	}

	@Override
	public void saveFile(byte[] buffer, String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists() && !file.createNewFile()) {
			return;
		}
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(buffer);
			log.info("Output file: {}", filePath);
		}
	}

	@Override
	public void saveFile(String str, String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists() && !file.createNewFile()) {
			return;
		}
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), SdtConst.DEFAULT_CHARSET)) {
			writer.write(str);
			log.info("Output file: {}", filePath);
		}
	}
}
