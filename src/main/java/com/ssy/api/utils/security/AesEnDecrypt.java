package com.ssy.api.utils.security;

import com.ssy.api.exception.SdtException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("restriction")
public class AesEnDecrypt {

	/**
	 * 
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月7日-下午1:35:49</li>
	 *         <li>功能说明：AES加密</li>
	 *         </p>
	 * @param content
	 *            要加密的字符串
	 * @param key
	 *            加密秘钥(必须为16位)
	 * @return	返回加密后的字符串
	 */
	public static String aesEncrypt(String content, String key) {
		try{
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度

			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(content.getBytes());
			return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		}catch (Exception e){
			throw new SdtException(e);
		}
	}

	/**
	 * 
	 * @Author sunshaoyu
	 *         <p>
	 *         <li>2019年3月7日-下午1:36:46</li>
	 *         <li>功能说明：AES解密</li>
	 *         </p>
	 * @param encrypted
	 *            需解密的字符串(加密后的字符串)
	 * @param key
	 *            解密秘钥(必须为16位)
	 * @return	返回解密后的字符串
	 */
	public static String aesDecrypt(String encrypted, String key) {
		try{
			byte[] raw = key.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());

			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(encrypted);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "UTF-8");

			return originalString;
		}catch (Exception e){
			throw new SdtException(e);
		}
	}
}
