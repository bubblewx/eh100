package com.eh100.web.book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.service.book.BookServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class GoAction extends ActionSupport  implements SessionAware  {
	

	private String target;
	
	private Integer id;
	
	private Map<String, Object> session;
















	public Integer getId() {
		return id;
	}










	public void setId(Integer id) {
		this.id = id;
	}










	public String getTarget() {
		return target;
	}










	public void setTarget(String target) {
		this.target = target;
	}










	public String execute() throws Exception {


		return "success";

	}










	public void setSession(Map<String, Object> arg0) {
		this.session = session;
		
	}


	



}
