package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.model.*;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order orderRequestToOrder(OrderRequest orderRequest) {
        return new Order(orderRequest.getDate(),orderRequest.getCustomerNumber());
    }

}
