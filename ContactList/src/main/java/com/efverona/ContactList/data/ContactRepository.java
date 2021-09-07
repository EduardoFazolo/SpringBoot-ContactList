package com.efverona.ContactList.data;

import com.efverona.ContactList.daos.ContactInfoDao;
import com.efverona.ContactList.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query(value = "SELECT new com.efverona.ContactList.daos.ContactInfoDao(c.id, c.name, c.lastName, c.kinshipDegree) FROM contacts c\n" +
			"WHERE c.name LIKE %:searchString% OR c.lastName LIKE %:searchString%", countQuery = "SELECT count (*) FROM contacts")
	Page<ContactInfoDao> findAllContactInfo(String searchString, Pageable pageable);
}
