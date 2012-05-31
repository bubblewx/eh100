package com.eh100.service.common;

import java.util.Collection;

import com.eh100.dao.book.BookDaoImpl;
import com.eh100.dao.common.AdminDao;
import com.eh100.entity.BookQuery;
import com.eh100.entity.BookVo;
import com.eh100.entity.Pagination;
import com.eh100.entity.UserVo;

public class AdminService {

	private AdminDao adminDao	;


	
	
    public UserVo getUser(final String  userId , final String passWord) {

    	return adminDao.getUser(userId, passWord);
    }

    
    
    public UserVo getUserByUserId(final String  userId) {

    	return adminDao.getUser(userId);
    }

    
    
    public UserVo showUserRelated(final String  userId) {

    	return adminDao.getUser(userId);
    }

    
    
    
    public void createUser(final UserVo  userVo ){
    	adminDao.createUser(userVo);
    }
    
    
    public void saveUser(final UserVo  userVo ){
    	adminDao.saveUser(userVo);
    }
    
    
	public AdminDao getAdminDao() {
		return adminDao;
	}



	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}



	
	
	
	
	
	
}
