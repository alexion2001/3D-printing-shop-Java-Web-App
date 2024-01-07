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
@RequestMapping("/filament")
@Api(value = "/filaments",
        tags = "Filaments",
        description = "Controller for managing filaments data")
public class FilamentController {

    private FilamentService filamentService;
    private FilamentMapper filamentMapper;

    @Autowired
    public FilamentController(FilamentService filamentService, FilamentMapper filamentMapper) {
        this.filamentService = filamentService;
        this.filamentMapper = filamentMapper;
    }

    @GetMapping
    @ApiOperation(value = "Dispaly all filaments",
            notes = "Displays all filaments together with all related data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Filament> getAllFilaments() {
        return  filamentService.getAllFilaments();

    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find filament by id",
            notes = "Dispaly filament based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Filament> getFilamentById(long id) {
        return  filamentService.findById(id);
    }


    @GetMapping(path = "/{type}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find filament by type",
            notes = "Dispaly filament based on the type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Filament> getFilamentByName(String type) {
        return  filamentService.findByType(type);
    }


    @PostMapping
    @ApiOperation(value = "Create a Filament",
            notes = "Creates a new Filament based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Filament> createFilament(
            @RequestBody
            @ApiParam(name = "filament", value = "Filament details", required = true)
                    FilamentRequest filamentRequest) {
        Filament savedEntry = filamentService.create(
                filamentMapper.filamentRequestToFilament(filamentRequest));
        return ResponseEntity
                .created(URI.create("/filament/" + savedEntry.getId()))
                .body(savedEntry);
    }


    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update a Filament data",
            notes = "Update an existing Filament data based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully updated based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Filament> updateFilament(
            @PathVariable @Parameter(name = "id",description = "Filament id",example = "1",required = true) Long id,
            @Valid
            @RequestBody
            @ApiParam(name = "filament", value = "Filament details", required = true)
                    FilamentRequest filamentRequest) {
        Filament filament = filamentMapper.filamentRequestToFilament(filamentRequest);
        return  ResponseEntity.ok(filamentService.updateFilament(id,filament));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a Filament",
            notes = "Delete an existing Filament based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully deleted based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public void deleteFilament(@PathVariable
                              @Parameter(name = "id",description = "Filament id",example = "1",required = true)
                                      Long id)
    {
        filamentService.deleteById(id);
    }
}
