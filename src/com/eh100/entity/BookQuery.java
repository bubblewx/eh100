package com.eh100.entity;

public class BookQuery {
	
	private String searchtype;
	

	
	private String keyword;

	private String searchWay;



	public String getSearchWay() {
		return searchWay;
	}




	public void setSearchWay(String searchWay) {
		this.searchWay = searchWay;
	}




	public boolean isLike() {
		return isLike;
	}




	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}



	private boolean  isLike;

	public String getBookName()
	{
		if("bookName".equals(searchtype)){
			return keyword;
		}
		else{
			return null;
		}
	}





public boolean isDisableSearchTypDiv(){
	return isFuzzySearchChecked();
}


public boolean isExactSearchChecked(){
	return !"fuzzy".equals(this.searchWay);
}


public boolean isFuzzySearchChecked(){
	return "fuzzy".equals(this.searchWay);
}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLike ? 1231 : 1237);
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result
				+ ((searchWay == null) ? 0 : searchWay.hashCode());
		result = prime * result
				+ ((searchtype == null) ? 0 : searchtype.hashCode());
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
		BookQuery other = (BookQuery) obj;
		if (isLike != other.isLike)
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (searchWay == null) {
			if (other.searchWay != null)
				return false;
		} else if (!searchWay.equals(other.searchWay))
			return false;
		if (searchtype == null) {
			if (other.searchtype != null)
				return false;
		} else if (!searchtype.equals(other.searchtype))
			return false;
		return true;
	}




	public String getSearchtype() {
		return searchtype;
	}



	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	
	

}
