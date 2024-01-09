package com.unibuc.shop.controllers;

import com.fasterxml.jackson.databind.*;

import com.unibuc.shop.controller.EmployeeController;
import com.unibuc.shop.dto.EmployeeRequest;
import com.unibuc.shop.mapper.EmployeeMapper;
import com.unibuc.shop.model.Employee;
import com.unibuc.shop.services.EmployeeService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeeController.class)

public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeMapper employeeMapper;

    EmployeeRequest employeeRequest = new EmployeeRequest( "maria", "0785212833", 2500, "proiectant");
    Employee employee = new Employee( 1L,"maria", "0785212833", 2500, "proiectant");

    @Test
    void getEmployeeById_returnsEmployee() {
        // Arrange
        long employeeId = 1L;
        Employee expectedEmployee = new Employee(employeeId, "John Doe", "123456789", 5000, "Developer");

        when(employeeService.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        // Act
        Optional<Employee> result =employeeService.findById(employeeId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedEmployee, result.get());

        verify(employeeService).findById(employeeId);
    }

    @Test
    void getEmployees_returnsListOfEmployees() {
        // Arrange
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1L, "John Doe", "123456789", 5000, "Developer"),
                new Employee(2L, "Jane Smith", "987654321", 6000, "Designer")
        );

        when(employeeService.getEmployees()).thenReturn(expectedEmployees);

        // Act
        List<Employee> result = employeeService.getEmployees();

        // Assert
        assertEquals(expectedEmployees.size(), result.size());
        assertEquals(expectedEmployees, result);
        
        verify(employeeService).getEmployees();
    }


    @Test
    public void createEmployee() throws Exception {
        EmployeeRequest request = this.employeeRequest;

        when(employeeService.create(any())).thenReturn(this.employee);

        mockMvc.perform(post("/employee")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value(request.getFullName()))
                .andExpect(jsonPath("$.job").value(request.getJob()))
                .andExpect(jsonPath("$.mobileNumber").value(request.getMobileNumber()))
                .andExpect(jsonPath("$.salary").value(request.getSalary()));
    }

    @Test
    public void updateEmployee() throws Exception{

        EmployeeRequest request = this.employeeRequest;
        Employee employee = this.employee;

        when(employeeService.updateEmployee(any(), any())).thenReturn(employee);

        mockMvc.perform(put("/employee/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(request.getFullName()))
                .andExpect(jsonPath("$.job").value(request.getJob()))
                .andExpect(jsonPath("$.mobileNumber").value(request.getMobileNumber()))
                .andExpect(jsonPath("$.salary").value(request.getSalary()));
    }
    @Test
    public void deleteEmployee() throws Exception{
        employeeService.deleteById(1L);
        mockMvc.perform(delete("/employee/{id}",1L)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
