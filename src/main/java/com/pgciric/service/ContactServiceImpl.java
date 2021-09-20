package com.pgciric.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgciric.entity.Contact;
import com.pgciric.repo.ContactRepo;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepo contactRepo;

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepo.save(contact);
	}

}
