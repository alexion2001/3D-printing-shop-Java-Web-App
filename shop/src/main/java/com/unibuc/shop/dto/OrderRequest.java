package com.unibuc.shop.dto;

import com.sun.istack.NotNull;
import com.unibuc.shop.model.Customer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "Order request", description = "Required details needed to create a new Order")
public class OrderRequest {

    @NotBlank
    @ApiModelProperty(value = "date", required = true, notes = "The date of the Order", example = "25 ian 2023", position = 1)
    private String date;

    @NotNull
    @ApiModelProperty(value = "customerId", required = true, notes = "The customer id of the Order", example = "1", position = 1)
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customerNumber;

    public OrderRequest(String date, Customer customerNumber) {
        this.date = date;
        this.customerNumber = customerNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }
}
