package com.unibuc.shop.dto;

import com.unibuc.shop.model.Order;
import com.unibuc.shop.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "Content request", description = "Required details needed to create a new Content")
public class ContentRequest {


    @NotBlank
    @ApiModelProperty(value = "product", required = true, notes = "The product of the Content", example = "", position = 1)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @NotBlank
    @ApiModelProperty(value = "order", required = true, notes = "The order of the Content", example = "", position = 2)

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    public ContentRequest() {

    }


    public ContentRequest(Product productId, Order orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }
}
