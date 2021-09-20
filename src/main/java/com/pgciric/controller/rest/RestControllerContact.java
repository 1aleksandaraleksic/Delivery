package com.pgciric.controller.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.entity.Contact;
import com.pgciric.service.ContactService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RestControllerContact {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/contact")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
				
		contact.setDate(new Date());
		
		contactService.saveContact(contact);
		
		return new ResponseEntity<Contact>(contact,HttpStatus.CREATED);
	}
	
}
