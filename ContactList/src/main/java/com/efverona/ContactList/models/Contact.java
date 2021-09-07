package com.efverona.ContactList.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "kinship_degree")
	private String kinshipDegree;

	public Contact() {}

	public Contact(String name, String lastName, String kinshipDegree) {
		this.name = name;
		this.lastName = lastName;
		this.kinshipDegree = kinshipDegree;
	}

	public Long getId() {
		return id;
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
}
