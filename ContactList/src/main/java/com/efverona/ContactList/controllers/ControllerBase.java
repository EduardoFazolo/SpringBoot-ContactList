package com.efverona.ContactList.controllers;

import com.efverona.ContactList.dtos.filters.PageableFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ControllerBase {

	<T extends PageableFilter> Pageable extractPageableFromFilter(T filter) {

		PageableFilter internalFilter = filter; // :(

		if (internalFilter == null)
			internalFilter = new PageableFilter();

		return PageRequest.of(internalFilter.getPageIndex(), internalFilter.getPageSize(), Sort.unsorted());
	}
}
