package com.eh100.dao.book;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.compass.core.Compass;
import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.core.CompassTransaction;
import org.compass.core.CompassQuery.SortDirection;
import org.compass.core.support.search.CompassSearchCommand;
import org.compass.core.support.search.CompassSearchHelper;
import org.compass.core.support.search.CompassSearchResults;
import org.compass.core.support.search.CompassSearchResults.Page;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.eh100.compass.common.Eh100CompassUtils;
import com.eh100.dao.common.CommonDaoImpl;
import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.util.DateUtil;
import com.eh100.web.book.LatestBookMenuAction;

public class BookDaoImpl  extends CommonDaoImpl implements BookDao{

	Logger logger = Logger.getLogger(BookDaoImpl.class);
    private SessionFactory sessionFactory;
    
    private CompassTemplate compassTemplate;
    private Compass compass;
    

    public Compass getCompass() {
		return compass;
	}



	public void setCompass(Compass compass) {
		this.compass = compass;
	}



	public CompassTemplate getCompassTemplate() {
		return compassTemplate;
	}



	public void setCompassTemplate(CompassTemplate compassTemplate) {
		this.compassTemplate = compassTemplate;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	public List<BookMenuVo> loadLatestHotBookMenues() {
    	
    	
    	callSp("getLatestBookMenu");

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (List<BookMenuVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {

				List<BookMenuVo> result = new ArrayList<BookMenuVo>();
				String sql = "select  distinct t1.id, t1.author, t1.bookname as bookName, t1.chapter  as  chapterName, t1.createdate as createDate  from teh100_hotbook hot inner join TEH100_BOOKMENU_temp t on t.bookname=hot.bookname left join  TEH100_BOOKMENU t1 on t.ordersequence = t1.ordersequence order by hot.hitcount desc";

				

				SQLQuery query = session.createSQLQuery(sql);
				
				query.addScalar("id",Hibernate.LONG);
				query.addScalar("author",Hibernate.STRING);
				query.addScalar("bookName",Hibernate.STRING);
				query.addScalar("chapterName",Hibernate.STRING);
				query.addScalar("createDate",Hibernate.DATE);
				query.setResultTransformer(Transformers.aliasToBean(BookMenuVo.class));
				

				return query.list();
				
			}
		});
	
		
    }
    
    
    
    public List<BookHotVo>  getHotBookByBookNames(final List<String> bookNames) {
    	
    	
    	  

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (List<BookHotVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {

				String sql = "select  hot.id, hot.author, hot.bookName, hot.hitcount as hitCount, menu.chapter as latestChapterName , hot.latestMenuId from teh100_hotbook  hot left join TEH100_BOOKMENU menu on hot.latestMenuid = menu.id  where hot.bookname in (:booknames) ";

		
				SQLQuery query = session.createSQLQuery(sql);
				query.addScalar("id",Hibernate.LONG);
				query.addScalar("author",Hibernate.STRING);
				query.addScalar("bookName",Hibernate.STRING);
				query.addScalar("hitCount",Hibernate.LONG);
				query.addScalar("latestChapterName",Hibernate.STRING);
				query.addScalar("latestMenuId",Hibernate.LONG);
				
				query.setResultTransformer(Transformers.aliasToBean(BookHotVo.class));
								
				query.setParameterList("booknames", bookNames);
	
				return query.list();
				 
				 
				
				
			}
		});
	
		
    }
    
    
    
    public BookHotVo  loadHotBook (final Long id) {
    	
    	
  	  

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (BookHotVo) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {

				String sql = "select  hot.id, hot.author, hot.bookName, hot.hitcount as hitCount, hot.latestMenuId from teh100_hotbook  hot  where hot.id=  (:id) ";

		
				SQLQuery query = session.createSQLQuery(sql);
				query.addScalar("id",Hibernate.LONG);
				query.addScalar("author",Hibernate.STRING);
				query.addScalar("bookName",Hibernate.STRING);
				query.addScalar("hitCount",Hibernate.LONG);

				query.addScalar("latestMenuId",Hibernate.LONG);
				
				query.setResultTransformer(Transformers.aliasToBean(BookHotVo.class));
								
				query.setParameter("id", id);
	
				return query.uniqueResult();
				 
				 
				
				
			}
		});
	
		
    }
    
    
    
    public Boolean isHotBook(final Long menuId ) {
    	
    	
    	  

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (Boolean) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {

				List<BookMenuVo> result = new ArrayList<BookMenuVo>();
				String sql = "select  1  from teh100_hotbook hot where hot.latestMenuId =(:menuId) ";

				
				sql +=" limit 1";
				Query query = session.createSQLQuery(sql);

				query.setLong("menuId", menuId);

				
				List resultList =(List)query.list();
				 if(resultList==null || resultList.isEmpty() ){
					 return false;
				 }
				 else{
					 return true;
				 }
				
			}
		});
	
		
    }
    
    
    
    public Boolean isHotBook(final String bookName , final String author) {
    	
    	
  

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (Boolean) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {

				List<BookMenuVo> result = new ArrayList<BookMenuVo>();
				String sql = "select  1  from teh100_hotbook where bookname =(:bookname) ";
				if(author!=null){
					sql +=" and author =(:author)";
				}
				
				sql +=" limit 1";
				Query query = session.createSQLQuery(sql);

				query.setString("bookname", bookName);
				if(author!=null){
				query.setString("author", author);
			
				}
				
				List resultList =(List)query.list();
				 if(resultList==null || resultList.isEmpty() ){
					 return false;
				 }
				 else{
					 return true;
				 }
				
			}
		});
	
		
    }
    
    
    
    
    public void callSp(final String spName) {
    	
    	


		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		  ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {

				List<BookMenuVo> result = new ArrayList<BookMenuVo>();
				String sql = "call "+spName+"()";

				

				Query query = session.createSQLQuery(sql);//.setResultTransformer(Transformers.aliasToBean(BookMenuVo.class));
				
							

				query.executeUpdate();
				
				
				
				return result;
			}
		});
	
		
    }
    
    
    public Collection<BookMenuVo> loadBookMenues(final Collection<Long> ids) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (Collection<BookMenuVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql = "from BookMenuVo book where book.id in (:ids)";

				

				Query query = session.createQuery(hql);
				
	
				query.setParameterList("ids", ids);
			

				return query.list();
			}
		});
	}
    
    
    
    public Collection<String> loadNewBooks(final Collection<String> booknames, final String siteName) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (Collection<String>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String sql = "select distinct bookname from TEH100_BOOK_RUNNING book where book.bookname in (:booknames) and book.targetSiteName =(:siteName)";

				

				Query query = session.createSQLQuery(sql);
				
	
				query.setParameterList("booknames", booknames);
				query.setString("siteName", siteName);
				
			

				return query.list();
			}
		});
	}
    

    
    public Map loadBooksForFuzzy(final Pagination pagination, final BookQuery bookQuery) {
		Map map = new HashMap();
			
		CompassSearchResults results  = (CompassSearchResults) getCompassTemplate().execute(
            new CompassCallback() {
            public Object doInCompass(CompassSession session) {
            	
            	CompassQueryBuilder queryBuilder = session.queryBuilder();
    
            		      		
            		CompassQuery compassQuery = queryBuilder
            	  .queryString(bookQuery.getKeyword())
            	   // .setAnalyzer("an1") // use a different analyzer
            	  .toQuery()
            	    .addSort("orderSequence", CompassQuery.SortPropertyType.STRING, CompassQuery.SortDirection.REVERSE);
            	    
            	 
            		
            		// constructs a new search helper with page size 10.
            		CompassSearchHelper searchHelper = new CompassSearchHelper(compass, pagination.getSize());

            		CompassSearchCommand compassSearchCommand = new CompassSearchCommand(compassQuery, new Integer(pagination.getCurrentPage()));
             		return  searchHelper.search(compassSearchCommand);
  		    
            
            }
        });
		
		
		List<BookVo> list = Eh100CompassUtils.buildVOFromCompassHits(results);

		pagination.setTotalPage(results.getPages().length);
		pagination.setTotalRecord(pagination.getSize()*results.getPages().length);
		for (int j = 0; j < results.getPages().length; j++) {
			Page page = results.getPages()[j];
			System.out.println(j + " " + page.getSize() + " " + page.getFrom()
					+ " " + page.getTo());
		}

		if (list != null) {

			map.put("result", list);
		}
		if (pagination != null)
			map.put("pagination", pagination);

		return map;

	}

  
    
    public Map loadBooks(final Pagination pagination , final BookQuery bookQuery) {

    	
    	Map map = new HashMap();
		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		Collection<BookVo> list =
         (Collection<BookVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql = apendQuery(bookQuery, "from BookVo book");

				Query queryForTotal = session.createQuery(
						"select count(*) " + hql);
				 
				if (bookQuery != null && bookQuery.getKeyword() != null
						&& !StringUtils.isEmpty(bookQuery.getKeyword().trim())) {

					queryForTotal.setString("keyword", bookQuery 
							.getKeyword().trim() + "%");
				}
				
				
				int totalRecord = ((Long)queryForTotal.uniqueResult()).intValue();
				 
				 
				int totalPage = totalRecord % pagination.getSize() == 0 ? totalRecord
						/ pagination.getSize()
						: totalRecord / pagination.getSize() + 1;
				pagination.setTotalRecord(totalRecord);
				pagination.setTotalPage(totalPage);
				hql += " order by book.orderSequence desc";
				final String hql1 = hql;

				Query query = session.createQuery(hql);
				
				if (bookQuery != null && bookQuery.getKeyword() != null
						&& !StringUtils.isEmpty(bookQuery.getKeyword().trim())) {

					query.setString("keyword", bookQuery
							.getKeyword().trim() + "%");
				}

				query.setFirstResult((pagination.getCurrentPage() - 1)
						* pagination.getSize());
				query.setMaxResults(pagination.getSize());

				return query.list();
			}
		});
		
		
		if(list!=null){
			
			map.put("result", list);
		}
		if(pagination!=null)
		map.put("pagination", pagination);
		
		return map;
	
	}

    
    
    
    public Collection<BookVo> loadHotBooksInfo(final String bookName , final String author) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (Collection<BookVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql =  "from BookVo book where book.bookName =(:bookName) and author = (:author) order by orderSequence desc";

		

				Query query = session.createQuery(hql);
				

				query.setString("bookName", bookName);
				query.setString("author", author);
				query.setMaxResults(50);
				return query.list();
			}
		});
	}
    
    
    
    public Collection<BookVo> loadBooks(final String bookName , final String author, final String targetSiteName) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (Collection<BookVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql =  "from BookVo book where book.bookName =(:bookName) and author = (:author) and targetSiteName =(:targetSiteName) order by orderSequence";

		

				Query query = session.createQuery(hql);
				

				query.setString("bookName", bookName);
				query.setString("author", author);
				query.setString("targetSiteName", targetSiteName);



				return query.list();
			}
		});
	}

    
    
    
    
    public Map loadBookMenues(final Pagination pagination , final BookQuery bookQuery) {
		
    	if("exact".equals(bookQuery.getSearchWay())){
    		return this.loadBookMenuesForExact(pagination, bookQuery);
    	}
    	else{
    	
    	Map map = new HashMap();
		logger.info("bookQuery" + bookQuery.getKeyword());
		CompassSearchResults results  = (CompassSearchResults) getCompassTemplate().execute(
            new CompassCallback() {
            public Object doInCompass(CompassSession session) {
            	
            	CompassQueryBuilder queryBuilder = session.queryBuilder();
    
            		      		
            		CompassQuery compassQuery = queryBuilder.
            	   queryString(bookQuery.getKeyword())
            	   // .setAnalyzer("an1") // use a different analyzer
            	  .toQuery()
            	  .addSort("createDate", CompassQuery.SortDirection.REVERSE)
            	  .addSort("id", CompassQuery.SortDirection.REVERSE);
                  
             	    
            	 
            		
            		// constructs a new search helper with page size 10.
            		CompassSearchHelper searchHelper = new CompassSearchHelper(compass, pagination.getSize());

            		CompassSearchCommand compassSearchCommand = new CompassSearchCommand(compassQuery, pagination.getCurrentPage()-1);
             		return  searchHelper.search(compassSearchCommand);
  		    
            
            }
        });
		
		
		List<BookMenuVo> list = Eh100CompassUtils.buildMenuVOFromCompassHits(results);

		pagination.setTotalPage(results.getPages().length);
		pagination.setTotalRecord(results.getTotalHits());


		if (list != null) {

			map.put("result", list);
		}
		if (pagination != null)
			map.put("pagination", pagination);

		return map;
    	}

	}
    
    
    
    public Map loadBookMenuesForExact(final Pagination pagination , final BookQuery bookQuery) {

    	Map map = new HashMap();
		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		Collection<BookMenuVo> list =  (Collection<BookMenuVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql = apendQuery(bookQuery, "from BookMenuVo book");

				Query queryForTotal = session.createQuery(
						"select count(*) " + hql);
				 
				if (bookQuery != null && bookQuery.getKeyword() != null
						&& !StringUtils.isEmpty(bookQuery.getKeyword().trim())) {

					queryForTotal.setString("keyword", bookQuery 
							.getKeyword() + "%");
				}
				
				
				int totalRecord = ((Long)queryForTotal.uniqueResult()).intValue();
				 
				 
				int totalPage = totalRecord % pagination.getSize() == 0 ? totalRecord
						/ pagination.getSize()
						: totalRecord / pagination.getSize() + 1;
				pagination.setTotalRecord(totalRecord);
				pagination.setTotalPage(totalPage);
				hql += " order by book.id desc";
				final String hql1 = hql;

				Query query = session.createQuery(hql);
				
				if (bookQuery != null && bookQuery.getKeyword() != null
						&& !StringUtils.isEmpty(bookQuery.getKeyword().trim())) {

					query.setString("keyword", bookQuery
							.getKeyword() + "%");
				}

				query.setFirstResult((pagination.getCurrentPage() - 1)
						* pagination.getSize());
				query.setMaxResults(pagination.getSize());

				return query.list();
			}
		});
		
		if(list!=null){
			
			map.put("result", list);
		}
		if(pagination!=null)
		map.put("pagination", pagination);
		
		return map;
	}
    
    public Map loadBooks(final Pagination pagination , final String bookName, final String bookAuthor, final String chapter) {

    	Map map = new HashMap();
		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		Collection<BookVo> list =  (Collection<BookVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql =  "from BookVo  where bookName=(:bookName)" ;
				
				if(bookAuthor!=null){
					hql += " and author =(:bookAuthor)";
				}

				
				if(chapter!=null){
					hql += " and chapterName =(:chapter)";
				}

				
				Query queryForTotal = session.createQuery(
						"select count(*) " + hql);
				 

				queryForTotal.setParameter("bookName", bookName);
				
				if(bookAuthor!=null){
					queryForTotal.setParameter("bookAuthor", bookAuthor);
				}

				
				if(chapter!=null){
					queryForTotal.setParameter("chapter", chapter);
				}

				
				
				int totalRecord = ((Long)queryForTotal.uniqueResult()).intValue();
				 
				 
				int totalPage = totalRecord % pagination.getSize() == 0 ? totalRecord
						/ pagination.getSize()
						: totalRecord / pagination.getSize() + 1;
				pagination.setTotalRecord(totalRecord);
				pagination.setTotalPage(totalPage);
				hql += " order by orderSequence desc";
				final String hql1 = hql;

				Query query = session.createQuery(hql);
				
				query.setParameter("bookName", bookName);
				
				if(bookAuthor!=null){
					query.setParameter("bookAuthor", bookAuthor);
				}

				
				if(chapter!=null){
					query.setParameter("chapter", chapter);
				}


				query.setFirstResult((pagination.getCurrentPage() - 1)
						* pagination.getSize());
				query.setMaxResults(pagination.getSize());

				return query.list();
			}
		});
		
		if(list!=null){
			
			map.put("result", list);
		}
		if(pagination!=null)
		map.put("pagination", pagination);
		
		return map;
	}
    
    
    

    
    
    
    public List<BookVo> loadBooks( final String bookName, final String chapterName) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return  (List<BookVo>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql =  "from BookVo book where book.chapterName = (:chapterName) and book.bookName = (:keyword) order by orderSequence desc";

				Query query = session.createQuery(
						 hql);

				query.setString("keyword", bookName);
				query.setString("chapterName", chapterName);
				query.setMaxResults(30);
				return query.list();
			}
		});
		
		
	}
    
    
    
    public <T> T load( final Class<T> className, final Long id) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return ht.get(className, id);		
	}
    
    
    
    private String apendQuery(BookQuery bookQuery, String sql){
    	String apendedSql = sql;
    	
    	if(bookQuery ==null || bookQuery.getKeyword() == null || StringUtils.isEmpty(bookQuery.getKeyword().trim()))
    		return sql;
    	
    	if("author".equals(bookQuery.getSearchtype())){
    		
    		return sql + " where book.author like (:keyword)";
    	}
    	
    	
    	else if("chapterName".equals(bookQuery.getSearchtype())){
    		
    		return sql + " where book.chapterName like (:keyword)";
    	}
    	else if("bookName".equals(bookQuery.getSearchtype())){
    		
    		return sql + " where book.bookName like (:keyword)";
    	}
    	
    	return sql;
    }
    
}
