package com.esi.orderservice.orders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.orderservice.orders.dto.OrderDto;
import com.esi.orderservice.orders.model.Order;
import com.esi.orderservice.orders.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders.stream().map(this::mapToOrderDTO).toList();
    }

    private OrderDto mapToOrderDTO(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .shippingAddress(order.getShippingAddress())
                .billingAddress(order.getBillingAddress())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

    public Optional<OrderDto> getOrder(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::mapToOrderDTO);
    }

    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(this::mapToOrderDTO)
                .toList();
    }

    public void addOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .customerId(orderDto.getCustomerId())
                .orderDate(orderDto.getOrderDate())
                .totalAmount(orderDto.getTotalAmount())
                .status(orderDto.getStatus())
                .shippingAddress(orderDto.getShippingAddress())
                .billingAddress(orderDto.getBillingAddress())
                .paymentMethod(orderDto.getPaymentMethod())
                .build();
        orderRepository.save(order);
        log.info("Order {} is added to the Database", order.getId());
    }

    public void updateOrder(String id, OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .customerId(orderDto.getCustomerId())
                .orderDate(orderDto.getOrderDate())
                .totalAmount(orderDto.getTotalAmount())
                .status(orderDto.getStatus())
                .shippingAddress(orderDto.getShippingAddress())
                .billingAddress(orderDto.getBillingAddress())
                .paymentMethod(orderDto.getPaymentMethod())
                .build();
        orderRepository.save(order);
        log.info("Order {} is updated", order.getId());
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
        log.info("A Order has been deleted");
    }
};

