package com.efverona.ContactList.services;

import com.efverona.ContactList.daos.ContactInfoDao;
import com.efverona.ContactList.data.ContactRepository;
import com.efverona.ContactList.data.DatabaseContext;
import com.efverona.ContactList.models.Contact;
import com.efverona.ContactList.models.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

	@Autowired
	private DatabaseContext databaseContext;

	public Page<Contact> findAll(Pageable pageable) {
		return databaseContext.getContactRepository().findAll(pageable);
	}

	public Page<ContactInfoDao> findAll(String searchString, Pageable pageable) {
		final Page<ContactInfoDao> allContactInfo = databaseContext.getContactRepository().findAllContactInfo(searchString, pageable);
		allContactInfo.forEach(e->{
			final List<Phone> phones = databaseContext.getPhoneRepository().findByContactId(e.getId());
			final List<String> phoneNumbers = phones.stream().map(Phone::getNumber).collect(Collectors.toList());
			e.setPhones(phoneNumbers);
		});
		return allContactInfo;
	}

	public Contact addNew(Contact contact) {
		return databaseContext.getContactRepository().save(contact);
	}

	public void remove(Long id) {
		databaseContext.getContactRepository().deleteById(id);
	}

	public Contact edit(Contact contact) {
		return databaseContext.getContactRepository().save(contact);
	}
}
