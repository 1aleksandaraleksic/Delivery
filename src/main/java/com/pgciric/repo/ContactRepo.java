package com.pgciric.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgciric.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

}
