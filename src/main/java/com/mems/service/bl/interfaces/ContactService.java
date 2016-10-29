package com.mems.service.bl.interfaces;

import com.mems.domain.Contact;

public interface ContactService {
	Contact findByName(String name);
	Contact findByEmail(String email);
	Contact findBySubject(String subject);
	Contact findById(Long id);
	void save(Contact contact);
	void delete(Contact contact);
}
