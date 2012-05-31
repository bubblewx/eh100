package com.eh100.web.book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.eh100.cache.common.EH100CacherManager;
import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.service.book.BookService;
import com.eh100.service.book.BookServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class LatestBookAction extends ActionSupport  implements SessionAware  {
	
	Logger logger = Logger.getLogger(LatestBookAction.class);
	private Collection<BookVo>  bookVos;
	
	private Pagination pagination;
	
	private BookService bookService;
	
	private Long bookMenuId;
	
	private Long bookId;
	
	private Long hotBookId;
	
	private String  bookIndentity;
	
	private  BookQuery  bookQuery;
	private String url;
	
	private BookHotVo bookHotVo ;
	
	private String bookName;
	
	private String siteName;
	
	private String author;
	
	private Boolean hotBook;
	
	private BookMenuVo bookMenuVo;
	
	private BookVo bookVo;
	
	public Boolean getHotBook() {
		return hotBook;
	}


	

	public  String getMetaContent(){
		
		if(bookMenuVo!=null){
		 return bookMenuVo.getBookName()+" " + bookMenuVo.getAuthor() + " 最新章节:" +bookMenuVo.getChapterName() ;
		}
		
		if(this.bookHotVo!=null){
		  return bookHotVo.getBookName()+" " + bookHotVo.getAuthor()  ;
				
		}
		
		return "最新章节";
		
	}
public  String getStaticContext(){
	
	String key = null;
	if( bookId!=null)
	{
		key =   "bookId_" + bookId + ".html";
	}
	
	if(this.bookMenuId!=null && bookId==null)
	{
		key = "bookMenuId_" + bookMenuId + ".html";
	}
	
	if(this.hotBookId!=null)
	{
		key = "hotBookId_" +hotBookId+ ".html";
	}
	
	if(key!=null){
		return EH100CacherManager.getInstance().getStaticHtml(key);
	}
	return null;
}

	public void setHotBook(Boolean hotBook) {
		this.hotBook = hotBook;
	}




	public String getBookName() {
		return bookName;
	}




	public void setBookName(String bookName) {
		this.bookName = bookName;
	}




	public String getSiteName() {
		return siteName;
	}




	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}




	public String getAuthor() {
		return author;
	}




	public void setAuthor(String author) {
		this.author = author;
	}




	private Map<String, Object> session;
	
	
	
	
	
	
	public String getBookIndentity() {
		return bookIndentity;
	}




	public void setBookIndentity(String bookIndentity) {
		this.bookIndentity = bookIndentity;
	}




	public Map<String, Object> getSession() {
		return session;
	}




	public void setSession(Map<String, Object> session) {
		this.session = session;
	}




	public Long getBookMenuId() {
		return bookMenuId;
	}




	public void setBookMenuId(Long bookMenuId) {
		this.bookMenuId = bookMenuId;
	}




	public String getUrl() {
		return url;
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


	
	
	public Collection<BookVo> getBookVos() {
		return bookVos;
	}




	public void setBookVos(Collection<BookVo> bookVos) {
		this.bookVos = bookVos;
	}




	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	
	
	public String loadMenusByBookAndSite() throws Exception {


		if(this.bookId !=null ){
			
			bookVo = this.getBookService().loadBook(bookId);
			this.bookIndentity = bookVo.getBookName()+ "_" +  bookVo.getAuthor();
			
		}

		    
		if(hotBook==null){
			hotBook = false;
			if(bookVo!=null){
				
				List<String> bookNames = new ArrayList<String>();
				bookNames.add(bookVo.getBookName());
				String key =bookVo.getBookName() + "_" + bookVo.getAuthor();
				
				
				Map<String, BookHotVo>  map = this.getBookService().getHotBookByBookNames(bookNames);
				BookHotVo bookHotVo = map.get(key);
				if(bookHotVo!=null&&bookVo.getChapterName().equals(bookHotVo.getLatestChapterName())){
					hotBook = true;
				}
			}
			else{
				hotBook = false;
			}
		}
		else{
			hotBook = false;
		}
		

		if(hotBook){
			return "successForHotBook";
		}
		bookVos = this.getBookService().loadBookMenuesByBookAuthorAndSiteName(bookVo.getBookName(), bookVo.getAuthor(), bookVo.getTargetSiteName());
		    

		return "menu";

	}
	
	
	public String execute() throws Exception {


		Map<String, Long>  maps = (Map<String, Long>) session.get("readedBook");
		if(maps == null){
			maps = new HashMap<String, Long>();
			session.put("readedBook", maps);
			
		}

		
		if(this.bookMenuId !=null ){
			
			bookMenuVo = this.getBookService().loadBookMenuVo(bookMenuId);
			this.bookIndentity = bookMenuVo.getBookName()+ "_" +  bookMenuVo.getAuthor();
			
		}
		if(this.bookMenuId !=null && this.bookIndentity!=null){
			maps.put(this.bookIndentity, bookMenuId);
		}
		
		    
		if(hotBook==null){
			
			if(bookMenuVo!=null){
			hotBook = this.getBookService().isHotBook(bookMenuId);
			}
			else{
				hotBook = false;
			}
		}
		else{
			hotBook = false;
		}
		
		
		if(this.hotBookId!=null){
			
			bookHotVo = this.getBookService().loadHotBook(hotBookId);
			return "successForHotBook";
			
		}
		
		if(hotBook){
			return "successForHotBook";
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
		url = "LatestBook.action";

		
		if(this.hotBookId!=null){
						
			bookVos = bookService.loadBooks(pagination , bookHotVo.getBookName(), bookHotVo.getAuthor(),null);
			return "success";
		
		}
		
		if(bookMenuVo!=null && bookQuery==null)
		{
			bookVos = bookService.loadBooks(pagination , bookMenuVo.getBookName(), bookMenuVo.getAuthor(), bookMenuVo.getChapterName());
		}
		else{
		
		bookVos = bookService.loadBooks(pagination , bookQuery);

		if (this.bookVos.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			bookVos = bookService.loadBooks(pagination,  bookQuery);
		}
		}
		return "success";

	}




	public BookQuery getBookQuery() {
		return bookQuery;
	}




	public void setBookQuery(BookQuery bookQuery) {
		this.bookQuery = bookQuery;
	}




	public Long getBookId() {
		return bookId;
	}




	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}




	public Long getHotBookId() {
		return hotBookId;
	}




	public void setHotBookId(Long hotBookId) {
		this.hotBookId = hotBookId;
	}




	public BookHotVo getBookHotVo() {
		return bookHotVo;
	}




	public void setBookHotVo(BookHotVo bookHotVo) {
		this.bookHotVo = bookHotVo;
	}







	
	
	
	
	
	



}
