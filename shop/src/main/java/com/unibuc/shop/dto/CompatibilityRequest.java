package com.unibuc.shop.dto;

import com.sun.istack.NotNull;
import com.unibuc.shop.model.Filament;
import com.unibuc.shop.model.Printer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ApiModel(value = "Compatibility request", description = "Required details needed to create a new Compatibility")
public class CompatibilityRequest {
    @NotNull
    @ApiModelProperty(value = "filament", required = true, notes = "The filament of the Compatibility", example = "", position = 1)
    @ManyToOne
    @JoinColumn(name = "filament_id")
    private Filament filamentId;

    @NotNull
    @ApiModelProperty(value = "printer", required = true, notes = "The printer of the Compatibility", example = "", position = 2)
    @ManyToOne
    @JoinColumn(name = "printer_id")
    private Printer printerId;

    public CompatibilityRequest() {

    }

    public CompatibilityRequest(Filament filamentId, Printer printerId) {
        this.filamentId = filamentId;
        this.printerId = printerId;
    }

    public Filament getFilamentId() {
        return filamentId;
    }

    public void setFilamentId(Filament filamentId) {
        this.filamentId = filamentId;
    }

    public Printer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Printer printerId) {
        this.printerId = printerId;
    }
}
