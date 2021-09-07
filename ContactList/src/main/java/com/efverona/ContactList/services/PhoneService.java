package com.efverona.ContactList.services;

import com.efverona.ContactList.data.DatabaseContext;
import com.efverona.ContactList.data.PhoneRepository;
import com.efverona.ContactList.models.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
	private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);

	@Autowired
	private DatabaseContext databaseContext;

	public List<Phone> findAllForContact(Long contactId) {
		return databaseContext.getPhoneRepository().findByContactId(contactId);
	}

	public void addAll(List<Phone> phones) {
		databaseContext.getPhoneRepository().saveAll(phones);
	}

	public void removeByContactId(Long id) {
		databaseContext.getPhoneRepository().removeByContactId(id);
	}

	public void edit(List<Phone> phones, Long contactId) {
		final PhoneRepository repository = databaseContext.getPhoneRepository();
		final List<Phone> byContactId = repository.findByContactId(contactId);
		repository.deleteAll(byContactId);
		repository.saveAll(phones);
	}
}
