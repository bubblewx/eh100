package com.eh100.dao.common;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.util.DateUtil;

public abstract  class CommonDaoImpl implements CommonDao{

	

	  abstract public SessionFactory getSessionFactory() ;

    
    public void callSp(final String spName) {
    	
    	


		HibernateTemplate ht = new HibernateTemplate(this.getSessionFactory());
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
    
  
    

    
    
    
  
    
}
