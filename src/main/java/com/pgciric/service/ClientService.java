package com.pgciric.service;

import java.util.List;

import com.pgciric.entity.Client;

public interface ClientService {
	
	public Client saveClient(Client client);
	public Client getById(int id);
	public void updateClient(Client client);
	public void deleteClient(int id);
	public List<Client> findAll();
	public List<Client> searchClient(String search);
	public List<Client> clientListByDelivery(int deliveryId);
	public List<Client> clientListOrderByOrders();

}
