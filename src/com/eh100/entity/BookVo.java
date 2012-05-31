package com.eh100.entity;

import java.util.Date;

import org.compass.annotations.SearchableProperty;

import com.eh100.util.Base64Utils;

public class BookVo extends Eh100CommonVo {
	
	@SearchableProperty(name="bookName")
	private String bookName;
	
	@SearchableProperty(name="author")
	private String author;
	
	@SearchableProperty(name="chapterName")
	private String chapterName;
	
	private Date createDate;
	
	private String chapterUrl;
	
	private String targetSiteUrl;
	
	private String targetSiteName;

	
	private String orderSequence;
	
	

	public String getBase64siteName(){
		return Base64Utils.getBASE64(this.getTargetSiteName());
	}

	
	public String getBase64bookName(){
		return Base64Utils.getBASE64(this.getBookName());
	}
	
	
	public String getOrderSequence() {
		return orderSequence;
	}

	public void setOrderSequence(String orderSequence) {
		this.orderSequence = orderSequence;
	}

	public String getTargetSiteName() {
		return targetSiteName;
	}

	public void setTargetSiteName(String targetSiteName) {
		this.targetSiteName = targetSiteName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getChapterUrl() {
		return chapterUrl;
	}

	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}

	public String getTargetSiteUrl() {
		return targetSiteUrl;
	}

	public void setTargetSiteUrl(String targetSiteUrl) {
		this.targetSiteUrl = targetSiteUrl;
	}


	
	
	

}
