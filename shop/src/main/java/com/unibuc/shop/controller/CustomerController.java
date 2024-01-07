package com.unibuc.shop.controller;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.mapper.*;
import com.unibuc.shop.model.*;
import com.unibuc.shop.services.*;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/customer")
@Api(value = "/customers",
        tags = "Customers",
        description = "Controller for managing customers data")
public class CustomerController {

    private CustomerService customerService;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    @ApiOperation(value = "Dispaly all customers",
            notes = "Displays all customers together with all related data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Customer> getAllCustomers() {
        return  customerService.getAllCustomers();

    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find customer by id",
            notes = "Dispaly customer based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Customer> getCustomerById(long id) {
        return  customerService.findById(id);
    }


    @GetMapping(path = "/{name}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find customer by name",
            notes = "Dispaly customer based on the name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Customer> getCustomerByFullName(String name) {
        return  customerService.findByFullName(name);
    }

    @PostMapping
    @ApiOperation(value = "Create a Customer",
            notes = "Creates a new Customer based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Customer> createCustomer(
            @RequestBody
            @ApiParam(name = "customer", value = "Customer details", required = true)
                    CustomerRequest customerRequest) {
        Customer savedEntry = customerService.create(
                customerMapper.customerRequestToCustomer(customerRequest));
        return ResponseEntity
                .created(URI.create("/customer/" + savedEntry.getCustomerId()))
                .body(savedEntry);
    }


    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update a Customer data",
            notes = "Update an existing Customer data based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully updated based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable @Parameter(name = "id",description = "Customer id",example = "1",required = true) Long id,
            @Valid
            @RequestBody
            @ApiParam(name = "customer", value = "Customer details", required = true)
                    CustomerRequest customerRequest) {
        Customer customer = customerMapper.customerRequestToCustomer(customerRequest);
        return  ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a Customer",
            notes = "Delete an existing Customer based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully deleted based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public void deleteCustomer(@PathVariable
                                 @Parameter(name = "id",description = "Customer id",example = "1",required = true)
                                         Long id)
    {
        customerService.deleteById(id);
    }
}
