package com.unibuc.shop.dto;

import com.unibuc.shop.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "Filament request", description = "Required details needed to create a new Filament")
public class FilamentRequest {

    @NotBlank
    @ApiModelProperty(value = "type", required = true, notes = "The type of the Filament", example = "red PLA", position = 1)
    private String type;

    @NotNull
    @ApiModelProperty(value = "pieces", required = true, notes = "The pieces number of the Filament", example = "red PLA", position = 2)
    private Integer piecesNumber;


    public FilamentRequest() {
    }

    public FilamentRequest(String type, Integer piecesNumber) {
        this.type = type;
        this.piecesNumber = piecesNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPiecesNumber() {
        return piecesNumber;
    }

    public void setPiecesNumber(Integer piecesNumber) {
        this.piecesNumber = piecesNumber;
    }
}
