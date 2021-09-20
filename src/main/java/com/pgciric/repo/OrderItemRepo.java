package com.pgciric.repo;

import org.springframework.data.repository.CrudRepository;

import com.pgciric.entity.OrderItem;

public interface OrderItemRepo extends CrudRepository<OrderItem, Integer> {

}
