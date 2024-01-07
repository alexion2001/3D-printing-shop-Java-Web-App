package com.unibuc.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Customer request", description = "Required details needed to create a new Customer")
public class CustomerRequest {

    @NotBlank
    @ApiModelProperty(value = "fullName", required = true, notes = "The name of the Customer", example = "John Doe", position = 1)
    private String fullName;

    @NotBlank
    @ApiModelProperty(value = "mobileNumber", required = true, notes = "The mobile number of the Customer", example = "0728282106", position = 2)
    private String mobileNumber;

    @NotBlank
    @ApiModelProperty(value = "email", required = true, notes = "The email of the Customer", example = "alex@gmail.com", position = 3)
    private String email;

    public CustomerRequest() {
    }

    public CustomerRequest(String fullName, String mobileNumber, String email) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
