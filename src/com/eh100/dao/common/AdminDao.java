package com.eh100.dao.common;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.entity.UserVo;
import com.eh100.util.DateUtil;

public class AdminDao {

	
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserVo getUser(final String  userId , final String passWord) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (UserVo) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql =  "from UserVo book where userId = :userId and password = :passWord";

				Query query = session.createQuery(
						hql);

				query.setString("userId", userId);
				
				query.setString("passWord", passWord);
				
				return ((UserVo)query.uniqueResult());
				 
		
			}
		});
	}

    
   
    
    public UserVo getUser(final String  userId ) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		return (UserVo) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					 {
				String hql =  "from UserVo book where userId = :userId ";

				Query query = session.createQuery(
						hql);

				query.setString("userId", userId);
				
				List<UserVo>  list = null;
				try{
				  list =(List<UserVo>)query.list();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				if(list!=null && !list.isEmpty())
					return list.get(0);
				else
					return null;
				 
		
			}
		});
	}
    
    
    public void createUser(final UserVo  userVo ) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		ht.saveOrUpdate(userVo);
	}

    public void saveUser(final UserVo  userVo ) {

		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
		ht.saveOrUpdate(userVo);
	}
    
    
}
