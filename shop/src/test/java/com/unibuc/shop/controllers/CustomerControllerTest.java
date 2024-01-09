package com.unibuc.shop.controllers;

import com.fasterxml.jackson.databind.*;

import com.unibuc.shop.controller.CustomerController;
import com.unibuc.shop.dto.CustomerRequest;
import com.unibuc.shop.mapper.CustomerMapper;
import com.unibuc.shop.model.Customer;
import com.unibuc.shop.services.CustomerService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)

public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private CustomerMapper customerMapper;

    CustomerRequest customerRequest = new CustomerRequest("maria","0728282106", "maria@gmail.com");
    Customer customer = new Customer(1L,"maria","0728282106", "maria@gmail.com");


    @Test
    public void createCustomer() throws Exception {
        CustomerRequest request = this.customerRequest;

        when(customerService.create(any())).thenReturn(this.customer);

        mockMvc.perform(post("/customer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value(request.getFullName()))
                .andExpect(jsonPath("$.email").value(request.getEmail()))
                .andExpect(jsonPath("$.mobileNumber").value(request.getMobileNumber()));

    }

    @Test
    public void updateCustomer() throws Exception{

        CustomerRequest request = this.customerRequest;
        Customer customer = this.customer;

        when(customerService.updateCustomer(any(), any())).thenReturn(customer);

        mockMvc.perform(put("/customer/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(request.getFullName()))
                .andExpect(jsonPath("$.email").value(request.getEmail()))
                .andExpect(jsonPath("$.mobileNumber").value(request.getMobileNumber()));

    }
    @Test
    public void deleteCustomer() throws Exception{
        customerService.deleteById(1L);
        mockMvc.perform(delete("/customer/{id}",1L)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
