package com.unibuc.shop.controller;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.mapper.*;
import com.unibuc.shop.model.*;
import com.unibuc.shop.services.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@Validated
@RequestMapping("/employee")
@Api(value = "/employee",
        tags = "Employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return  employeeService.getEmployees();

    }

    @PostMapping
    @ApiOperation(value = "Create an Employee",
            notes = "Creates a new Employee based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Employee> create(
            @RequestBody
            @ApiParam(name = "employee", value = "Employee details", required = true)
                    EmployeeRequest employeeRequest) {
        Employee savedEntry = employeeService.create(
                employeeMapper.employeeRequestToEmployee(employeeRequest));
        return ResponseEntity
                .created(URI.create("/employee/" + savedEntry.getEmployeeId()))
                .body(savedEntry);
    }
}
