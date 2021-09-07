package com.efverona.ContactList.dtos.filters;

public class PageableFilter {

	private int pageIndex = 0;

	private int pageSize = 5;

	public int getPageIndex() {
		return this.pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSkip() {
		return pageIndex * pageSize;
	}
}
