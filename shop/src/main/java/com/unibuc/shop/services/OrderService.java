package com.unibuc.shop.services;

import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByDate(String date) {
        return orderRepository.findByDate(date);
    }


    public List<Order> findByCustomerId(long customerId) {
        List<Order> result = new ArrayList<>();
        orderRepository.findAll().stream()
                .filter(order -> order.getCustomerId().getCustomerId() == customerId)
                .forEach(result::add);
        return result;
    }


    public Order create(Order order) {
        return orderRepository.save(order);
    }


}
