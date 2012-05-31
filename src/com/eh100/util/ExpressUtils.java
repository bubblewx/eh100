package com.eh100.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

public class ExpressUtils {

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


         System.out.print(checkHasEngilishMark("把英文标点符号替换成空,即去掉英文标点符号")); //把英文标点符号替换成空，即去掉英文标点符号


		
		//System.out.print(Base64Utils.getFromBASE64("5Lit5paH5rWL6K+V"));
	}
	
	
	public static boolean checkHasEngilishMark(String value) {
		Pattern p = Pattern.compile(".*[.,\"\\?!:'].*");// 增加对应的标点

		Matcher m = p.matcher(value);

		return m.matches();
	} 
	
	public static  boolean checkChineseName(String value) {
		return value.matches("^[\u4e00-\u9fa5]+$");
		} 

		/**
		* 检查 email输入是否正确
		* 正确的书写格 式为 username@domain
		* @param value
		* @return
		*/
		public boolean checkEmail(String value, int length) {
		return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")&&value.length()<=length;
		} 

}
