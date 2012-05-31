package com.eh100.entity;

public class Pagination {
	private int start;
	// һ��ȡ�õ���
	private int size;
	// Ҫȡ��ҳ��
	private int currentPage = 1;
	// �ܵļ�¼ҳ��
	private int totalPage = 0;
	// �ܵļ�¼����
	private int totalRecord;

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Pagination() {

	}

	public Pagination(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + size;
		result = prime * result + start;
		result = prime * result + totalPage;
		result = prime * result + totalRecord;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		if (currentPage != other.currentPage)
			return false;
		if (size != other.size)
			return false;
		if (start != other.start)
			return false;
		if (totalPage != other.totalPage)
			return false;
		if (totalRecord != other.totalRecord)
			return false;
		return true;
	}

	
	public  void setValues(Pagination pagination){
		this.currentPage = pagination.currentPage;
		this.size=pagination.size;
		this.start=pagination.start;
		this.totalPage=pagination.totalPage;
		this.totalRecord=pagination.totalRecord;
		
	}
}