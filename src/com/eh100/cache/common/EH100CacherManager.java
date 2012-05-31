package com.eh100.cache.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.engin.EhScanner;

public class EH100CacherManager {

	
	static final String constant_static_folder="D:\\eh100\\htmlfile2\\latestUpdate\\";
	static Map<String, SoftReference<String>> cache = new  HashMap<String, SoftReference<String>>();
	private static Logger logger_ = Logger.getLogger(EH100CacherManager.class);
	
	static private EH100CacherManager instance;
	
	static public EH100CacherManager  getInstance(){
		
		if(instance == null)
		{instance = new EH100CacherManager();
			
		}
		
			return instance;
		
	}
	
	public void putStaticHtml(String filePath){
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					new FileInputStream(constant_static_folder+filePath), "UTF-8"));

	
		StringBuilder strBuilder = new StringBuilder(); 
		for(String str = in.readLine(); str!=null;  str = in.readLine()){
			strBuilder.append(str);
		}

		
		cache.put(filePath, new SoftReference<String>(strBuilder.toString()));
		
	} catch (Exception e) {		
		logger_.error(e);
	} 
	finally{
		
		if(in!=null){
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	}

	
	public String getStaticHtml(String filePath){
		
		SoftReference<String>  value = cache.get(filePath);
		
		if(value == null || value.get()==null){
		 this.putStaticHtml(filePath);
		}
		return cache.get(filePath).get();
	}
	
	

}
