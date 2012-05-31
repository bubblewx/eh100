package com.eh100.web.book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.service.book.BookService;
import com.eh100.util.CollectionUtil;
import com.eh100.util.ExpressUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class LatestBookMenuAction extends ActionSupport implements SessionAware {
	
	Logger logger = Logger.getLogger(LatestBookMenuAction.class);
	private Collection<BookMenuVo>  bookVos;
	
	private Collection<BookMenuVo>  bookVosReaded;
	
	private Pagination pagination;
	
	
	private String warningMsg;
	
	
	public String getWarningMsg() {
		return warningMsg;
	}




	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}




	private BookService bookService;
	
	private Map<String, Object> session;
	
	private  BookQuery  bookQuery;
	private String url;
	
	
	
	public String getUrl() {
		return url;
	}




	public Collection<BookMenuVo> getBookVosReaded() {
		return bookVosReaded;
	}




	public void setBookVosReaded(Collection<BookMenuVo> bookVosReaded) {
		this.bookVosReaded = bookVosReaded;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public Pagination getPagination() {
		return pagination;
	}




	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}




	public BookService getBookService() {
		return bookService;
	}


	
	
	public Collection<BookMenuVo> getBookVos() {
		return bookVos;
	}




	public void setBookVos(Collection<BookMenuVo> bookVos) {
		this.bookVos = bookVos;
	}




	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	private void mappingHotBookId(Collection<BookMenuVo> vos){
		
		List<String> bookNames = CollectionUtil.getList(vos, "bookName");
		
		Map<String, BookHotVo> map = this.bookService.getHotBookByBookNames(bookNames);
	

		for(BookMenuVo each : vos )
		{

			String key = each.getBookName()+ "_" + each.getAuthor();
			
			BookHotVo bookHotVo = map.get(key);
			
			if(bookHotVo!=null){
			
			each.setHotBookId(bookHotVo.getId());
			}
		}
	}
	
	public String execute() throws Exception {


		
		Map<String, Long> maps = (Map<String, Long>) session.get("readedBook");
		
		if(maps!=null && maps.values()!=null && !maps.values().isEmpty()){
			bookVosReaded = this.getBookService().loadBookMenues(maps.values());
			mappingHotBookId(bookVosReaded);
		}
		    
		int size = 50;
		if (pagination == null) {
			pagination = new Pagination(size);
		}
		pagination.setSize(size);
		if (pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		
		logger.info("current page is " +  pagination.getCurrentPage());
		
		if (pagination.getTotalPage() != 0
				&& pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		url = "LatestBookMenu.action";

		if(bookQuery.getKeyword()==null || "".equals(bookQuery.getKeyword().trim()) || bookQuery.getKeyword().trim().length()<2){
			this.warningMsg="请输入至少两个字符！";
			return "success"; 
		}
		
		if(ExpressUtils.checkHasEngilishMark(bookQuery.getKeyword())){
			this.warningMsg="请不要输入英文标点！";
			return "success"; 
		}
		bookVos = bookService.loadBookMenus(pagination , bookQuery);
 
		if (this.bookVos.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			bookVos = bookService.loadBookMenus(pagination,  bookQuery);
		}
		
		if(bookVos!=null && !bookVos.isEmpty()){
		mappingHotBookId(bookVos);
		
		}
		return "success";

	}



	
	public String latestReadBook() throws Exception {


		Map<String, Long> maps = (Map<String, Long>) session.get("readedBook");
		
		if(maps!=null && maps.values()!=null && !maps.values().isEmpty()){
			bookVosReaded = this.getBookService().loadBookMenues(maps.values());
			mappingHotBookId(bookVosReaded);
		
		}
		    

		return "success";

	}

	
	

	public BookQuery getBookQuery() {
		return bookQuery;
	}




	public void setBookQuery(BookQuery bookQuery) {
		this.bookQuery = bookQuery;
	}




	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}







	
	
	
	
	
	



}
