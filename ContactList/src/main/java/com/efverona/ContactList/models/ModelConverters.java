package com.efverona.ContactList.models;

import com.efverona.ContactList.daos.ContactInfoDao;
import com.efverona.ContactList.dtos.ContactDto;

import java.util.ArrayList;
import java.util.List;

public class ModelConverters {
	public static class ContactConverter {

		public static Contact toContact(ContactDto dto) {
			final Contact contact = new Contact();
			contact.setId(dto.getId());
			contact.setName(dto.getName());
			contact.setLastName(dto.getLastName());
			contact.setKinshipDegree(dto.getKinshipDegree());
			return contact;
		}

		public static List<ContactDto> toDto(List<ContactInfoDao> contacts) {
			final ArrayList<ContactDto> dtos = new ArrayList<>();
			for(ContactInfoDao contact: contacts) {
				dtos.add(toDto(contact));
			}
			return dtos;
		}

		public static ContactDto toDto(ContactInfoDao contact) {
			final ContactDto dto = new ContactDto();
			dto.setId(contact.getId());
			dto.setName(contact.getName());
			dto.setLastName(contact.getLastName());
			dto.setKinshipDegree(contact.getKinshipDegree());
			dto.setPhones(contact.getPhones());
			return dto;
		}

		public static List<Phone> toPhone(ContactDto info, Long contactId) {
			final ArrayList<Phone> phones = new ArrayList<>();
			for(String number: info.getPhones()) {
				final Phone phone = new Phone();
				phone.setNumber(number);
				phone.setContactId(contactId);
				phones.add(phone);
			}
			return phones;
		}
	}
}
