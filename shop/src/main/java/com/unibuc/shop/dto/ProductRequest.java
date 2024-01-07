package com.unibuc.shop.dto;

import com.unibuc.shop.model.Filament;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Product request", description = "Required details needed to create a new Product")
public class ProductRequest {

    @NotBlank
    @ApiModelProperty(value = "name", required = true, notes = "The name of the Product", example = "Lamp", position = 1)
    private String name;


    @NotNull
    @ApiModelProperty(value = "price", required = true, notes = "The price of the Product", example = "250", position = 2)
    private double price;

    private Filament filamentType;

    public ProductRequest(String name, double price, Filament filamentType) {
        this.name = name;
        this.price = price;
        this.filamentType = filamentType;
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

    public Filament getFilamentType() {
        return filamentType;
    }

    public void setFilamentType(Filament filamentType) {
        this.filamentType = filamentType;
    }
}
