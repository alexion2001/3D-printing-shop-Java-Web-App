package com.unibuc.shop.service;


import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.Customer;
import com.unibuc.shop.model.Order;
import com.unibuc.shop.repository.OrderRepository;
import com.unibuc.shop.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    Order order = new Order(1L,"25 ian 2023",new Customer(1L,"maria","0728282106", "maria@gmail.com"));


    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;


    @Test
    void whenOrderDoesntExist_create_savesTheOrder() {

        // Arrange
        Order order = this.order;

        Order savedOrder = this.order;
        when(orderRepository.save(order)).thenReturn(savedOrder);

        // Act
        Order result = orderService.create(order);

        // Assert
        assertNotNull(result);
        assertEquals(savedOrder.getOrderId(), result.getOrderId());
        assertEquals(savedOrder.getDate(), result.getDate());
        assertEquals(order.getCustomerId(), result.getCustomerId());
        verify(orderRepository).save(order);
    }

    @Test
    void whenOrderDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Order> result = orderService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void whenOrderExists_findById_returnsTheOrder() {
        // Arrange
        Order order = new Order();
        order.setOrderId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = orderService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(order.getOrderId(), result.get().getOrderId());
    }



}
