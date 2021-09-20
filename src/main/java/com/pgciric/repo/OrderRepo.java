package com.pgciric.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pgciric.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

	@Query("select o from Order o where o.delivery.id=:id")
	public List<Order> getOrdersByDelivery(int id);
	
}
