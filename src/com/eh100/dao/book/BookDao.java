package com.eh100.dao.book;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.eh100.dao.common.CommonDao;
import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.util.DateUtil;

public interface BookDao extends CommonDao {

    public BookHotVo  loadHotBook (final Long id) ;
	public Map loadBooks(final Pagination pagination, final String bookName,
			final String bookAuthor, final String chapter);

	


	
	
	public Boolean isHotBook(final Long menuId);

	public Boolean isHotBook(final String bookName, final String author);

	public List<BookVo> loadBooks(final String bookName,
			final String chapterName);

	public Collection<BookVo> loadHotBooksInfo(final String bookName,
			final String author);

	public Collection<BookMenuVo> loadBookMenues(final Collection<Long> ids);

	public <T> T load(final Class<T> className, final Long id);

	public Collection<String> loadNewBooks(final Collection<String> booknames,
			final String siteName);

	public Map loadBooks(final Pagination pagination, final BookQuery bookQuery);

	public Collection<BookVo> loadBooks(final String bookName,
			final String author, final String targetSiteName);

	public Map loadBookMenues(final Pagination pagination,
			final BookQuery bookQuery);

	public List<BookMenuVo> loadLatestHotBookMenues();

	public List<BookHotVo> getHotBookByBookNames(final List<String> bookNames);
	
	

}
