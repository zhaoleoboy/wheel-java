package com.leo.wheel.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 关于加密的工具类
 * @author leo
 *
 */
public class EncryptUtils {

	public static String encodeByBase64(String source) {
		long start = System.currentTimeMillis();
		BASE64Encoder encoder = new BASE64Encoder();
		String result = encoder.encode(source.getBytes());
		long end = System.currentTimeMillis();
		System.out.println(String.format("Base64加密花费的时间为%sms", end - start));
		return result;
	}

	public static String decodeByBase64(String dest) throws IOException {
		long start = System.currentTimeMillis();
		BASE64Decoder decoder = new BASE64Decoder();
		String result = new String(decoder.decodeBuffer(dest));
		long end = System.currentTimeMillis();
		System.out.println(String.format("Base64加密花费的时间为%sms", end - start));
		return result;
	}

	/**
	 * MD5加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64Encoder = new BASE64Encoder();
		// 加密字符串
		String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(EncryptUtils.encodeByBase64("testttttttt"));
		System.out.println(EncryptUtils.decodeByBase64("dGVzdHR0dHR0dHQ="));
	}
}
