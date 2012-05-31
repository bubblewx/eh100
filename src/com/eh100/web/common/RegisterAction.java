package com.eh100.web.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.entity.UserVo;
import com.eh100.service.book.BookServiceImpl;
import com.eh100.service.common.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class RegisterAction extends ActionSupport implements  ServletResponseAware {
	
	Logger logger = Logger.getLogger(RegisterAction.class);

	
	private HttpServletResponse response;

	private AdminService adminService;
	
	private  UserVo  userVo;
	



    public void setServletResponse(HttpServletResponse response)
    {
    	this.response = response;
    }

	public String list() throws Exception {
	
		return "input";
	}
	
	
	public String save() throws Exception {
		
		this.adminService.createUser(userVo);
		
		return "success";
	}


	public AdminService getAdminService() {
		return adminService;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	public UserVo getUserVo() {
		return userVo;
	}


	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	

	
	
	public String remoteCheck() {
		   boolean b = false;

		   if (null == userVo)
		    b = true;
		   else {
		    logger.info("AJAX验证用户：" + userVo.getConfirmPassword());
		    UserVo userinfo = this.adminService.getUserByUserId(userVo.getUserId());
		    if (null != userinfo) {
			     b = true;
		    } 		   }
		   return renderText(b ? "false" : "true");
		}

		protected String render(String text, String contentType) {
		   try {

		    response.setContentType(contentType);
		    response.getWriter().write(text);
		   } catch (IOException e) {
		   }
		   return null;
		}

		/**
		* 直接输出字符串.
		*/
		protected String renderText(String text) {
		   return render(text, "text/plain;charset=UTF-8");
		}

		

}
