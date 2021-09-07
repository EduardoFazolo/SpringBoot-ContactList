package com.efverona.ContactList.controllers;

import com.efverona.ContactList.daos.ContactInfoDao;
import com.efverona.ContactList.dtos.ContactDto;
import com.efverona.ContactList.dtos.filters.SearchPageableFilter;
import com.efverona.ContactList.models.Contact;
import com.efverona.ContactList.models.ModelConverters;
import com.efverona.ContactList.models.Phone;
import com.efverona.ContactList.services.ContactService;
import com.efverona.ContactList.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/contacts")
public class ContactsController extends ControllerBase {

	@Autowired
	private ContactService contactService;

	@Autowired
	private PhoneService phoneService;

	@GetMapping
	public ResponseEntity<List<ContactDto>> listContacts(SearchPageableFilter filter) {
		try {
			final Pageable pageable = extractPageableFromFilter(filter);
			final List<ContactInfoDao> contacts = contactService.findAll(filter.getSearchString(), pageable).getContent();
			final List<ContactDto> contactDtos = ModelConverters.ContactConverter.toDto(contacts);
			return ResponseEntity.ok(contactDtos);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<String> addContact(@RequestBody ContactDto dto) {
		try {
			final Contact contact = ModelConverters.ContactConverter.toContact(dto);
			final Long id = contactService.addNew(contact).getId();
			final List<Phone> phones = ModelConverters.ContactConverter.toPhone(dto, id);
			phoneService.addAll(phones);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity<String> removeContact(@RequestParam("id") Long id) {
		try {
			phoneService.removeByContactId(id);
			contactService.remove(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PutMapping
	@Transactional
	public ResponseEntity<String> editContact(ContactDto contactDto) {
		try {
			final Contact contact = ModelConverters.ContactConverter.toContact(contactDto);
			final Contact edit = contactService.edit(contact);
			final List<Phone> phones = ModelConverters.ContactConverter.toPhone(contactDto, edit.getId());
			phoneService.edit(phones);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
