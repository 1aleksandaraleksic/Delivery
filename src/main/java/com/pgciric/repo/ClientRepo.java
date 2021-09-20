package com.pgciric.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pgciric.entity.Client;
import com.pgciric.entity.ClientDTO;

public interface ClientRepo extends JpaRepository<Client, Integer> {
	
	@Query("select c from Client c where concat (c.name, c.adress, c.municipality) like %?1%")
	public List<Client> searchClient(@Param("search") String search);
	
	@Query("select c from Client c "
			+ "right join Order o on o.client.id = c.id "
			+ "inner join delivery d on o.delivery.id = d.id "
			+ "where d.id =:deliveryId "
			+ "order by c.municipality")
	public List<Client> clientListByDelivery(@Param("deliveryId") int deliveryId);


	@Query("select new com.pgciric.entity.ClientDTO (c.id,c.name,c.adress,c.municipality,c.detailed,c.number,count(o.client.id)) from Client c "
			+ "join Order o on c.id = o.client.id "
			+ "group by c.name "
			+ "order by count(o.client.id) desc")
	public List<ClientDTO> clientListOrderByOrders();
	
	@Query("select new com.pgciric.entity.ClientDTO (c.id,c.name,c.adress,c.municipality,c.detailed,c.number,count(o.client.id)) from Client c "
			+ "join Order o on o.client.id = c.id "
			+ "group by c.id")
	public List<ClientDTO> findAllClientDTO();

}
