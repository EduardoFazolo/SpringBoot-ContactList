package com.efverona.ContactList.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "contact_id")
	private Long contactId;

	@Column(name = "number", nullable = false)
	private String number;

	public Phone() {
	}

	public Phone(Long contactId, String number) {
		this.contactId = contactId;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
