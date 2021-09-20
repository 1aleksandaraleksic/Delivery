package com.pgciric.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.entity.Client;
import com.pgciric.exception.NotFoundException;
import com.pgciric.repo.ClientRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clients")
public class RestControllerClient {

	@Autowired
	ClientRepo clientRepo;
	

	@GetMapping("")
	public List<Client> getAllClient(){
		return clientRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Client getClientById(@PathVariable int id) {
		Client client = clientRepo.getOne(id);
		if(client == null) {
			throw new NotFoundException("not found client");
		}
		return client;
	}
	
	@PostMapping("/")
	public Client addClient(@RequestBody Client client) {

		return clientRepo.save(client);
	}
	
	@PutMapping("/")
	public Client updateClient(@PathVariable int id ,@RequestBody Client client) {
		
		Client c = clientRepo.findById(id).orElseThrow(() -> new NotFoundException("Ne postoji klijent sa id: "+id));
		
		c.setName(client.getName());
		c.setAdress(client.getAdress());
		c.setMunicipality(client.getMunicipality());
		c.setNumber(client.getNumber());
		c.setDetailed(client.getDetailed());
		
		return clientRepo.save(client);			
	}
	
	@DeleteMapping("/{id}")
	public MessageResponse deleteClient(@PathVariable int id) {
		
		
		Client c = clientRepo.getOne(id);
		
		if(c == null) {
			throw new NotFoundException("Not client for id"+id);
		} 

		clientRepo.deleteById(id);
		
		return new MessageResponse(HttpStatus.OK.value(), "Success deleted", System.currentTimeMillis());
	}

}
