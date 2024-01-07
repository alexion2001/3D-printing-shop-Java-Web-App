package com.unibuc.shop.controller;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.mapper.*;
import com.unibuc.shop.model.*;
import com.unibuc.shop.services.*;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/printer")
@Api(value = "/printers",
        tags = "Printers",
        description = "Controller for managing printers data")
public class PrinterController {

    private PrinterService printerService;
    private PrinterMapper printerMapper;

    @Autowired
    public PrinterController(PrinterService printerService, PrinterMapper printerMapper) {
        this.printerService = printerService;
        this.printerMapper = printerMapper;
    }

    @GetMapping
    @ApiOperation(value = "Dispaly all printers",
            notes = "Displays all printers together with all related data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Printer> getAllPrinters() {
        return  printerService.getAllPrinters();

    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find printer by id",
            notes = "Dispaly printer based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Printer> getPrinterById(long id) {
        return  printerService.findById(id);
    }


    @GetMapping(path = "/{name}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find printer by name",
            notes = "Dispaly printer based on the name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Printer> getPrinterByName(String name) {
        return  printerService.findByName(name);
    }


    @PostMapping
    @ApiOperation(value = "Create a Printer",
            notes = "Creates a new Printer based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Printer> createPrinter(
            @RequestBody
            @ApiParam(name = "printer", value = "Printer details", required = true)
                    PrinterRequest printerRequest) {
        Printer savedEntry = printerService.create(
                printerMapper.printerRequestToPrinter(printerRequest));
        return ResponseEntity
                .created(URI.create("/printer/" + savedEntry.getPrinterId()))
                .body(savedEntry);
    }


    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update a Printer data",
            notes = "Update an existing Printer data based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully updated based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Printer> updatePrinter(
            @PathVariable @Parameter(name = "id",description = "Printer id",example = "1",required = true) Long id,
            @Valid
            @RequestBody
            @ApiParam(name = "printer", value = "Printer details", required = true)
                    PrinterRequest printerRequest) {
        Printer printer = printerMapper.printerRequestToPrinter(printerRequest);
        return  ResponseEntity.ok(printerService.updatePrinter(id,printer));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a Printer",
            notes = "Delete an existing Printer based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully deleted based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public void deletePrinter(@PathVariable
                              @Parameter(name = "id",description = "Printer id",example = "1",required = true)
                                      Long id)
    {
        printerService.deleteById(id);
    }
}
