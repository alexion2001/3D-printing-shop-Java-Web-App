package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.model.*;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product productRequestToProduct(ProductRequest productRequest) {
        return new Product(productRequest.getName(), productRequest.getPrice(), productRequest.getFilamentType());
    }

}
