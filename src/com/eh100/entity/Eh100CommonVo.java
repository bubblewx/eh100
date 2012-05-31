package com.eh100.entity;

import org.compass.annotations.SearchableId;

public class Eh100CommonVo {
	@SearchableId
	private Long  id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
