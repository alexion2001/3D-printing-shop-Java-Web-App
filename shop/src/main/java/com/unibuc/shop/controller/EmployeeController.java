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
@RequestMapping("/employee")
@Api(value = "/employees",
        tags = "Employees",
        description = "Controller for managing employees data")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    @ApiOperation(value = "Dispaly all employees",
            notes = "Displays all employees together with all related data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Employee> getEmployees() {
        return  employeeService.getEmployees();

    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find employee by id",
            notes = "Dispaly employee based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Employee> getEmployeeById(long id) {
        return  employeeService.findById(id);
    }


    @GetMapping(path = "/{fullName}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find employee by full name",
            notes = "Dispaly employee based on his full name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Employee> getEmployeeByFullName(String fullName) {
        return  employeeService.findByFullName(fullName);
    }


    @PostMapping
    @ApiOperation(value = "Create an Employee",
            notes = "Creates a new Employee based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Employee> createEmployee(
            @RequestBody
            @ApiParam(name = "employee", value = "Employee details", required = true)
                    EmployeeRequest employeeRequest) {
        Employee savedEntry = employeeService.create(
                employeeMapper.employeeRequestToEmployee(employeeRequest));
        return ResponseEntity
                .created(URI.create("/employee/" + savedEntry.getEmployeeId()))
                .body(savedEntry);
    }


    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update an Employee data",
            notes = "Update an existing Employee data based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully updated based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable @Parameter(name = "id",description = "Employee id",example = "1",required = true) Long id,
            @Valid
            @RequestBody
            @ApiParam(name = "employee", value = "Employee details", required = true)
                    EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
        return  ResponseEntity.ok(employeeService.updateEmployee(id,employee));
    }
}
