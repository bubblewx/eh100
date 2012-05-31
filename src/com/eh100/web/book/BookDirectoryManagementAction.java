package com.eh100.web.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.entity.UserVo;
import com.eh100.service.book.BookService;
import com.eh100.service.book.BookServiceImpl;
import com.eh100.service.common.AdminService;
import com.eh100.util.CollectionUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class BookDirectoryManagementAction extends ActionSupport implements SessionAware , RequestAware {
	
	Logger logger = Logger.getLogger(BookDirectoryManagementAction.class);

	private Map<String, Object>  request;


	public void setRequest(Map<String, Object> request) {
		this.request = request;
		
	}


	private AdminService adminService;
	
	
	private BookService bookService;
	
	private  UserVo  userVo;
	
	private List<String> deletedIds;
	
	private List<String> addIds;

	private Map<String,Object> session;

	
	

	public BookService getBookService() {
		return bookService;
	}


	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	public List<String> getAddIds() {
		return addIds;
	}


	public void setAddIds(List<String> addIds) {
		this.addIds = addIds;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}


	public String list() throws Exception {
		
		String userId = (String) session.get("userId");
		if (userId != null) {
			userVo = this.adminService.getUserByUserId(userId);
			
			
			Collection<BookMenuVo> vos = userVo.getBookMenuVos();
			List<String> bookNames = CollectionUtil.getList(vos, "bookName");
			Map<String, BookHotVo> map = this.bookService.getHotBookByBookNames(bookNames);

			for(BookMenuVo each : vos){
				
				String key = each.getBookName()+"_" + each.getAuthor();
				each.setLatestBookHotVo(map.get(key));
			}
			
			return "input";
		} else {
			return "login";
		}
	}
	
	
	public String addBooksToDirectory() throws Exception {
		
		String userId = (String)session.get("userId");
		
		if (userId == null) {
			return "login";
		}
		userVo = this.adminService.getUserByUserId(userId);
		
		Set<String> set =new HashSet<String>();
		
		if(addIds!=null)
		{
			set.addAll(addIds);
		}
		
		
		if(userVo.getBookMenuVos()==null){
			
			userVo.setBookMenuVos(new HashSet<BookMenuVo>());
		}
		
		if(userVo.getBookMenuVos()!=null){
			
			Set<BookMenuVo> bookMenuVos =userVo.getBookMenuVos();
			
			for(String each: set){
				BookMenuVo bookMenuVo =  new BookMenuVo();
				bookMenuVo.setId(Long.valueOf(each));
				bookMenuVos.add(bookMenuVo);

				
			}
		}
		this.adminService.saveUser(userVo);
		
		return "input";
	}
	
	
	public String deleteBooksFromDirectory() throws Exception {
		
		String userId = (String)session.get("userId");
		userVo = this.adminService.getUserByUserId(userId);
		
		Set<String> set =new HashSet<String>();
		
		if(deletedIds!=null)
		{
			set.addAll(deletedIds);
		}
		
		if(userVo.getBookMenuVos()!=null){
			
			Set<BookMenuVo> bookMenuVos =userVo.getBookMenuVos();
			
			for(Iterator<BookMenuVo> iter=bookMenuVos.iterator(); iter.hasNext();){
				
				BookMenuVo bookMenuVo = iter.next();
				
				if(bookMenuVo.getId()!=null){
					String idstr = bookMenuVo.getId().toString();
					if(set.contains(idstr))
					{
						iter.remove();
					}
				}
				
			}
		}
		this.adminService.saveUser(userVo);
		
		return "input";
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


	public List<String> getDeletedIds() {
		return deletedIds;
	}


	public void setDeletedIds(List<String> deletedIds) {
		this.deletedIds = deletedIds;
	}
	

	
	
		

}
