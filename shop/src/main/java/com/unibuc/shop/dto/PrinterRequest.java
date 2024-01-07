package com.unibuc.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Printer request", description = "Required details needed to create a new Printer")
public class PrinterRequest {

    @NotBlank
    @ApiModelProperty(value = "type", required = true, notes = "The type of the Printer", example = "Odisseus", position = 1)
    private String name;

    public PrinterRequest() {
    }

    public PrinterRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
