package com.esi.orderservice.orders.repository;

import org.springframework.data.repository.CrudRepository;

import com.esi.orderservice.orders.model.Order;

public interface OrderRepository extends CrudRepository<Order, String>{

}
