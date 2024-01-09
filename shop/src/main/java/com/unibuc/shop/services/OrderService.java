package com.unibuc.shop.services;

import com.unibuc.shop.dto.ProductDTO;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ContentRepository contentRepository;

    public OrderService(OrderRepository orderRepository, ContentRepository contentRepository) {
        this.orderRepository = orderRepository;
        this.contentRepository = contentRepository;
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

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public List<ProductDTO> getOrderContent(long id) {
        return contentRepository.findProductsByOrderId_OrderId(id).stream()
                .map(content -> new ProductDTO(content.getProductId().getName(), content.getProductId().getPrice()))
                .collect(Collectors.toList());
    }


}
