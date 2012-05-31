package com.eh100.entity;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class UserVo extends Eh100CommonVo {

	private String userId;
	
	private String  userName;
	
	private String  confirmPassword;
	
	private String  passWord;
	
	private String email;
	
	private String bookIdReaded;

	public String getBookIdReaded() {
		return bookIdReaded;
	}

	public void setBookIdReaded(String bookIdReaded) {
		this.bookIdReaded = bookIdReaded;
	}

	
	public void appendBookIdReaded(String bookIdReaded) {
		if (bookIdReaded != null && !StringUtils.isEmpty(bookIdReaded)) {
			this.bookIdReaded = this.bookIdReaded + bookIdReaded;
		}

	}
	
	
	private Date createDate;
	
	private Set<BookMenuVo> bookMenuVos;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<BookMenuVo> getBookMenuVos() {
		return bookMenuVos;
	}

	public void setBookMenuVos(Set<BookMenuVo> bookMenuVos) {
		this.bookMenuVos = bookMenuVos;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
	
	
}
