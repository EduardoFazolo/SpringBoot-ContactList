package com.efverona.ContactList.config;

import com.efverona.ContactList.data.ContactRepository;
import com.efverona.ContactList.data.PhoneRepository;
import com.efverona.ContactList.models.Contact;
import com.efverona.ContactList.models.Phone;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ContactConfig {
	@Bean
	CommandLineRunner commandLineRunner(ContactRepository repository, PhoneRepository phoneRepository) {
		return args -> {
			final Contact john = new Contact("John", "Test", "Filho");
			final Contact ada = new Contact("Ada", "Test", "Irmã");

			final Phone phone = new Phone(2L, "99999999");

			repository.saveAll(List.of(john, ada));
			phoneRepository.save(phone);
		};
	}
}
