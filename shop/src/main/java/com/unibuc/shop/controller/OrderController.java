package com.unibuc.shop.controller;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.mapper.*;
import com.unibuc.shop.model.*;
import com.unibuc.shop.services.*;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/order")
@Api(value = "/orders",
        tags = "Orders",
        description = "Controller for managing orders data")
public class OrderController {

    private OrderService orderService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    @ApiOperation(value = "Dispaly all orders",
            notes = "Displays all orders together with all related data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Order> getAllOrders() {
        return  orderService.getAllOrders();

    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find order by id",
            notes = "Dispaly order based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Order> getOrderById(long id) {
        return  orderService.findById(id);
    }


    @GetMapping(path = "/{date}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find orders by date",
            notes = "Dispaly orders based on the date")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Order> getOrderByName(String date) {
        return  orderService.findByDate(date);
    }

    @GetMapping(path = "/{customerid}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find orders by customer",
            notes = "Dispaly orders based on the customer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Order> getOrderByCustomer(long id) {
        return  orderService.findByCustomerId(id);
    }


    @PostMapping
    @ApiOperation(value = "Create a Order",
            notes = "Creates a new Order based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Order> createOrder(
            @RequestBody
            @ApiParam(name = "order", value = "Order details", required = true)
                    OrderRequest orderRequest) {
        Order savedEntry = orderService.create(
                orderMapper.orderRequestToOrder(orderRequest));
        return ResponseEntity
                .created(URI.create("/order/" + savedEntry.getOrderId()))
                .body(savedEntry);
    }


    

   
}
