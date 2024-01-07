package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.ContentRequest;
import com.unibuc.shop.model.Content;

public class ContentMapper {
    public Content contentRequestToContent(ContentRequest contentRequest) {
        return new Content(contentRequest.getProductId(),contentRequest.getOrderId());
    }
}
