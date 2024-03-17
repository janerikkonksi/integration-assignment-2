package com.esi.orderservice.orders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.orderservice.orders.dto.OrderDto;
import com.esi.orderservice.orders.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/orders")
    public List<OrderDto> getAllorders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<OrderDto> getorder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderDto> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @PostMapping("/orders")
    public void addorder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @PutMapping("/orders/{id}")
    public void updateorder(@RequestBody OrderDto orderDto, @PathVariable String id) {
        orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteorder(@PathVariable String id) {
        orderService.deleteOrder(id);
    }
}
