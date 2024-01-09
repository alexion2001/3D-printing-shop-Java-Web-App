package com.unibuc.shop.controllers;

import com.fasterxml.jackson.databind.*;

import com.unibuc.shop.controller.OrderController;
import com.unibuc.shop.dto.OrderRequest;
import com.unibuc.shop.mapper.ContentMapper;
import com.unibuc.shop.mapper.OrderMapper;
import com.unibuc.shop.model.Customer;
import com.unibuc.shop.model.Order;
import com.unibuc.shop.services.OrderService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class)

public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderMapper orderMapper;
    @MockBean
    private ContentMapper contentMapper;

    OrderRequest orderRequest = new OrderRequest( "25 ian 2023",new Customer(1L,"maria","0728282106", "maria@gmail.com"));
    Order order = new Order(1L,"25 ian 2023",new Customer(1L,"maria","0728282106", "maria@gmail.com"));

    @Test
    public void createOrder() throws Exception {
        OrderRequest request = this.orderRequest;

        when(orderService.create(any())).thenReturn(this.order);

        mockMvc.perform(post("/order/order")  // Change 'get' to 'post'
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date").value(request.getDate()));


    }

}
