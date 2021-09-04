package com.gxa.xly2021.util;

import java.security.MessageDigest;

public class MD5Util {

	public static String MD5(String s) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes("utf-8"));
	        return toHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	private static String toHex(byte[] bytes) {

	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}

	// 所以我们需要一个加强版
	// 加盐
	// 自定义
	public static String MD55(String str){
		// 先利用MD5加密
		String pwd = MD5(str);
		// 破坏密文结构
		return pwd.substring(0,4)+pwd.substring(29)+pwd.substring(20, 29)+pwd.substring(4, 20);
	}






}
