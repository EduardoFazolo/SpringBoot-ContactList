package com.efverona.ContactList.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseContext {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	public ContactRepository getContactRepository() {
		return contactRepository;
	}

	public PhoneRepository getPhoneRepository() {
		return phoneRepository;
	}
}
