package com.eh100.service.book;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eh100.dao.book.BookDao;
import com.eh100.dao.book.BookDaoImpl;
import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.util.CollectionUtil;

public class BookServiceImpl implements BookService{

	private BookDao bookDao	;

	
	
	
	
	public BookVo loadBook(Long id) {
		// TODO Auto-generated method stub
		return bookDao.load(BookVo.class, id);
	}

	
	


    
    
    public Collection<BookVo> loadBooks(final Pagination pagination , final String bookName, final String bookAuthor, final String chapter) {
    	
		Map map = bookDao.loadBooks( pagination, bookName, bookAuthor, chapter);
		pagination.setValues( (Pagination)map.get("pagination"));
		return (Collection<BookVo>)map.get("result");
    }


	public BookMenuVo loadBookMenuVo(Long id) {
		// TODO Auto-generated method stub
		return bookDao.load(BookMenuVo.class, id);
	}


	public Collection<BookVo> loadBooks(Pagination pagination,BookQuery bookQuery) 
	
	{
		Map map = bookDao.loadBooks( pagination, bookQuery);
		pagination.setValues( (Pagination)map.get("pagination"));
		return (Collection<BookVo>)map.get("result");
		

	}
	
	
	public Collection<BookVo> loadBookMenuesByBookAuthorAndSiteName(String bookName,String author, String siteName) 
	
	{
		
		return bookDao.loadBooks( bookName, author, siteName);
	}
	
	
	
	public Collection<BookMenuVo> loadBookMenues(Collection<Long> ids) 
	
	{
		
		return bookDao.loadBookMenues( ids);
	}
	
	
	
	public Collection<BookMenuVo> loadBookMenus(Pagination pagination,BookQuery bookQuery) 
	
	{
		
		Map map = bookDao.loadBookMenues( pagination, bookQuery);
		pagination.setValues( (Pagination)map.get("pagination"));
		return (Collection<BookMenuVo>)map.get("result");
	}
	
	
	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	
	 public Boolean isHotBook( String bookName ,  String author) {
		return this.bookDao.isHotBook(bookName, author);
	 }
	 
	 

	    public Boolean isHotBook( Long menuId ) {
	    	return this.bookDao.isHotBook(menuId);
	    }
	 
	 public Map<String, BookHotVo>  getHotBookByBookNames(final List<String> bookNames) {
		 
		 List<BookHotVo> list = this.bookDao.getHotBookByBookNames(bookNames);
		 
		 Map<String, BookHotVo> map = new HashMap<String, BookHotVo>();
		 
		 if (list != null && !list.isEmpty()) {
			for (BookHotVo vo : list) {
				String key = vo.getBookName() + "_" + vo.getAuthor();
				map.put(key, vo);
			}
		}
		 return map;
	 }


	public BookHotVo loadHotBook(Long id) {
		// TODO Auto-generated method stub
		return this.bookDao.loadHotBook(id);
	}
	
	 
	 
	
}
