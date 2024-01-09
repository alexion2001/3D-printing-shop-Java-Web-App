package com.unibuc.shop.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Product", example = "Lamp", position = 1)
    private String name;


    @NotNull
    @ApiModelProperty(value = "price", required = true, notes = "The price of the Product", example = "250", position = 2)
    private double price;

    public ProductDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
