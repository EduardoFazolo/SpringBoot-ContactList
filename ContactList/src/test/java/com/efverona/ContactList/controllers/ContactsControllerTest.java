package com.efverona.ContactList.controllers;

import com.efverona.ContactList.dtos.ContactDto;
import com.efverona.ContactList.dtos.filters.SearchPageableFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class ContactsControllerTest {

	@Autowired
	private ContactsController contactsController;

	@Test
	void listContactsShouldNotBeEmpty() {
		final ResponseEntity<List<ContactDto>> response = contactsController.listContacts(new SearchPageableFilter());
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(2, response.getBody().size());
	}

	@Test
	void addNewUserShouldWork() {
		final ResponseEntity<String> response = contactsController.addContact(createContact("Name", "Test", "Filho"));
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	void shouldListContactsAfterAdding() {
		contactsController.addContact(createContact("Eduardo", "Test", "Filho"));
		final List<ContactDto> contacts = contactsController.listContacts(new SearchPageableFilter()).getBody();
		assertNotNull(contacts);
		assertEquals(3, contacts.size());
		final List<ContactDto> filteredResult = contacts.stream().filter(c -> c.getName().equals("Eduardo")).collect(Collectors.toList());
		assertNotNull(filteredResult);
		assertNotNull(filteredResult.get(0));
	}

	@Test
	void removeContactShouldWork() {
		List<ContactDto> contacts = contactsController.listContacts(new SearchPageableFilter()).getBody();
		assertNotNull(contacts);
		assertEquals(2, contacts.size());
		final ResponseEntity<String> response = contactsController.removeContact(2L);
		assertEquals(200, response.getStatusCodeValue());
		contacts = contactsController.listContacts(new SearchPageableFilter()).getBody();
		assertNotNull(contacts);
		assertEquals(1, contacts.size());
	}

	@Test
	void editShouldChangeContact() {
		List<ContactDto> contacts = contactsController.listContacts(new SearchPageableFilter()).getBody();
		assertNotNull(contacts);
		assertEquals(2, contacts.size());
		ContactDto contact = contacts.get(0);
		contact.setName("EditedName");
		final ResponseEntity<String> response = contactsController.editContact(contact);
		assertEquals(200, response.getStatusCodeValue());

		final SearchPageableFilter filter = new SearchPageableFilter();
		filter.setSearchString("EditedName");
		contacts = contactsController.listContacts(filter).getBody();
		assertNotNull(contacts);
		assertEquals(1, contacts.size());
	}

	private ContactDto createContact(String name, String lastName, String degree) {
		final ContactDto dto = new ContactDto();
		dto.setName(name);
		dto.setLastName(lastName);
		dto.setKinshipDegree(degree);
		dto.setPhones(new ArrayList<>(Arrays.asList("9999999", "88888888")));
		return dto;
	}
}
