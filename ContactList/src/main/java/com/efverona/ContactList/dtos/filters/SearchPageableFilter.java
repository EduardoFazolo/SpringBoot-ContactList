package com.efverona.ContactList.dtos.filters;

public class SearchPageableFilter extends PageableFilter {

	private String searchString = "";

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
