package com.eh100.entity;

import java.util.Date;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;

import com.eh100.util.Base64Utils;
@Searchable
public class BookHotVo extends Eh100CommonVo {
	
	@SearchableProperty(name="bookName")
	private String bookName;
	
	private String author;
	
	private String latestChapterName;
	
	
	private Long latestMenuId;

	
	private Long hitCount;


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


	public String getLatestChapterName() {
		return latestChapterName;
	}


	public void setLatestChapterName(String latestChapterName) {
		this.latestChapterName = latestChapterName;
	}




	public Long getLatestMenuId() {
		return latestMenuId;
	}


	public void setLatestMenuId(Long latestMenuId) {
		this.latestMenuId = latestMenuId;
	}


	public Long getHitCount() {
		return hitCount;
	}


	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}
	

	

}
