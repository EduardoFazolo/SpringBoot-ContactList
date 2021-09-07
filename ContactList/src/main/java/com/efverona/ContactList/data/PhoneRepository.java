package com.efverona.ContactList.data;

import com.efverona.ContactList.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

	List<Phone> findByContactId(@Param("contactId") Long contactId);

	@Modifying
	@Query(value = "DELETE FROM phones p WHERE p.contactId = :id")
	void removeByContactId(@Param("id") Long id);
}
