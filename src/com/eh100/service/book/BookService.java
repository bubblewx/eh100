package com.eh100.service.book;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.eh100.dao.book.BookDaoImpl;
import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;


public interface BookService {

    public Boolean isHotBook( Long menuId ) ;
    	

	public BookVo loadBook(Long id) ;
	public BookMenuVo loadBookMenuVo(Long id) ;
	
	public Collection<BookVo> loadBooks(Pagination pagination,BookQuery bookQuery) ;
	
	public Collection<BookVo> loadBooks(final Pagination pagination , final String bookName, final String bookAuthor, final String chapter) ;
	
	
	

	
	public Collection<BookVo> loadBookMenuesByBookAuthorAndSiteName(String bookName,String author, String siteName) ;
	

	 public Boolean isHotBook( String bookName ,  String author) ;
		  
	
	public Collection<BookMenuVo> loadBookMenues(Collection<Long> ids) ;
	

	
	
	public Collection<BookMenuVo> loadBookMenus(Pagination pagination,BookQuery bookQuery) ;
	

    public Map<String, BookHotVo>  getHotBookByBookNames(final List<String> bookNames) ;
	

    public BookHotVo  loadHotBook (final Long id) ;
	
}
