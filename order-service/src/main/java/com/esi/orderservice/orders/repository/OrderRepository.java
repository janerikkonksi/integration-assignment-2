package com.esi.orderservice.orders.repository;

import org.springframework.data.repository.CrudRepository;

import com.esi.orderservice.orders.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String>{
    List<Order> findByCustomerId(Long customerId);
}
