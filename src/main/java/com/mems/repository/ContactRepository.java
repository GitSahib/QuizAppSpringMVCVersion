package com.mems.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mems.domain.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

	Contact findByName(String name);
	Contact findByEmail(String email);
	Contact findBySubject(String subject);
	Contact findById(Long id);
}
