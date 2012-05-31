package com.eh100.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import sun.misc.BASE64Decoder;

public class Base64Utils {

	public static String getBASE64(String s) {
		return s;
//		if (s == null)
//			return null;
//		try {
//			return URLEncoder.encode((new sun.misc.BASE64Encoder()).encode(s
//					.getBytes()), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static void  main (String[] args ){
		String str = null;
		
		try {
			 str=java.net.URLEncoder.encode("中文","UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(str);
		
		//System.out.print(Base64Utils.getFromBASE64("5Lit5paH5rWL6K+V"));
	}

}
