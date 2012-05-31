package com.eh100.entity;

import java.util.Date;

import com.eh100.util.Base64Utils;

public class BookMenuVo extends Eh100CommonVo {
	

	private String bookName;
	
	private String author;
	
	private String chapterName;
	
	private Date createDate;
	
	private String orderSequence;
	
	private int updateCount;
	
	
	private Long hotBookId;
	
	private BookHotVo latestBookHotVo;
	
	




public BookHotVo getLatestBookHotVo() {
		return latestBookHotVo;
	}



	public void setLatestBookHotVo(BookHotVo latestBookHotVo) {
		this.latestBookHotVo = latestBookHotVo;
	}



public String getBase64bookName(){
	return Base64Utils.getBASE64(this.getBookName());
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

	public String getOrderSequence() {
		return orderSequence;
	}

	public void setOrderSequence(String orderSequence) {
		this.orderSequence = orderSequence;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}



	public Long getHotBookId() {
		return hotBookId;
	}



	public void setHotBookId(Long hotBookId) {
		this.hotBookId = hotBookId;
	}



	

	
	
	

}
