package com.eh100.web.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.entity.UserVo;
import com.eh100.service.book.BookServiceImpl;
import com.eh100.service.common.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class LoginAction extends ActionSupport implements SessionAware   {
	
	Logger logger = Logger.getLogger(LoginAction.class);
	
	private String userId;
	
	private String passWord;
	
	
	private Map<String, Object> session;

	
	private String loginFail;
	
	private AdminService adminService;
	

	
	

	public String getLoginFail() {
		return loginFail;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getPassWord() {
		return passWord;
	}




	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}




	public AdminService getAdminService() {
		return adminService;
	}




	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}



	
	






	public String save() throws Exception {


			UserVo user = this.adminService.getUser(userId, passWord);
			if(user!=null){
				
				this.session.put("userId", userId);
				return "success";
			}
			else {
				addActionError("请检查用户名和密码");
				loginFail = "Yes";
				return "input";
			}

	}



	public String list() throws Exception {
		
		return "input";
	}




	public void setSession(Map<String, Object> session) {
		
		this.session = session;
		// TODO Auto-generated method stub
		
	}









	
	
	
	
	
	



}
