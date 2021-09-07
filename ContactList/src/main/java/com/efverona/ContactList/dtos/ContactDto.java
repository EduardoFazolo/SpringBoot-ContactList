package com.efverona.ContactList.dtos;

import java.util.List;

public class ContactDto {

	private Long id;
	private String name;
	private String lastName;
	private String kinshipDegree;
	private List<String> phones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getKinshipDegree() {
		return kinshipDegree;
	}

	public void setKinshipDegree(String kinshipDegree) {
		this.kinshipDegree = kinshipDegree;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}
