package com.ssy.api.utils.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * <p>
 * 文件功能说明： md5相关处理
 * </p>
 * 
 * @Author sunshaoyu
 *         <p>
 *         <li>2019年3月7日-下午4:00:01</li>
 *         <li>修改记录</li>
 *         <li>-----------------------------------------------------------</li>
 *         <li>标记：修订内容</li>
 *         <li>2019年3月7日-sunshaoyu：创建注释模板</li>
 *         <li>-----------------------------------------------------------</li>
 *         </p>
 */
public class Md5Encrypt {

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "A", "B", "C",
			"D", "F", "E", "G", "H", "I", "J", "K" };

	
	/**
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月7日-下午4:00:12</li>
	 *         <li>功能说明：字节数组转十六进制</li>
	 *         </p>
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	
	/**
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月7日-下午4:00:22</li>
	 *         <li>功能说明：字节转十六进制</li>
	 *         </p>
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	
	/**
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月7日-下午4:00:53</li>
	 *         <li>功能说明：字符串进行md5加密</li>
	 *         </p>
	 * @param origin
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5EncodeStr(String origin) {
		if (null == origin) {
			throw new NullPointerException();
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byteArrayToHexString(md.digest(origin.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月7日-下午4:07:04</li>
	 *         <li>功能说明：获取文件的md5值</li>
	 *         </p>
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5EncodeFile(File file) throws IOException, NoSuchAlgorithmException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			StringBuffer fileContent = new StringBuffer();
			byte[] buffer = new byte[1024];

			while ((in.read(buffer)) != -1) {
				fileContent.append(new String(buffer, "UTF-8"));
			}
			return md5EncodeStr(fileContent.toString());
		}finally {
			if(null != in){
				in.close();
				in = null;
			}
		}
	}
}
