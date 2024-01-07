package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.model.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer customerRequestToCustomer(CustomerRequest customerRequest) {
        return new Customer(customerRequest.getFullName(), customerRequest.getMobileNumber(), customerRequest.getEmail());
    }

}
