package com.unibuc.shop.service;


import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.Employee;
import com.unibuc.shop.repository.EmployeeRepository;
import com.unibuc.shop.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    Employee employee = new Employee(1L,"maria","0785212833",2500,"proiectant");


    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void whenEmployeeAlreadyExists_create_throwsDuplicateException() {
        // Arrange
        Employee employee = new Employee();
        employee.setFullName("John Doe");
        when(employeeRepository.findByFullName(employee.getFullName()))
                .thenReturn(Optional.of(employee));

        // Act
        DuplicateException exception = assertThrows(DuplicateException.class,
                () -> employeeService.create(employee));

        // Assert
        assertEquals("An entry with the same data already exists.", exception.getMessage());
        verify(employeeRepository, times(0)).save(employee);

    }
    @Test
    void whenEmployeeDoesntExist_create_savesTheEmployee() {

        // Arrange
        Employee employee = new Employee();
        employee.setFullName("maria");

        Employee savedEmployee = this.employee;
//        savedEmployee.setFullName("maria");
//        savedEmployee.setSalary(2500);
//        savedEmployee.setJob("executant");
//        savedEmployee.setMobileNumber("0728282106");
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);

        // Act
        Employee result = employeeService.create(employee);

        // Assert
        assertNotNull(result);
        assertEquals(savedEmployee.getEmployeeId(), result.getEmployeeId());
        assertEquals(savedEmployee.getFullName(), result.getFullName());
        assertEquals(employee.getFullName(), result.getFullName());
        verify(employeeRepository).findByFullName(employee.getFullName());
        verify(employeeRepository).save(employee);
    }

    @Test
    void whenEmployeeDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Employee> result = employeeService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void whenEmployeeExists_update() {
        // Arrange
        Employee oldEmployee = new Employee(1L, "maria", "0785212833", 2500, "proiectant");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(oldEmployee));

        Employee newEmployee = this.employee;
        newEmployee.setSalary(3500);

        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        // Act
        Employee result = employeeService.updateEmployee(1L, newEmployee);

        // Assert
        assertNotNull(result);
        assertEquals(newEmployee.getEmployeeId(), result.getEmployeeId());
        assertEquals(newEmployee.getFullName(), result.getFullName());
        assertEquals(newEmployee.getJob(), result.getJob());
        assertEquals(newEmployee.getSalary(), result.getSalary());
        assertEquals(newEmployee.getMobileNumber(), result.getMobileNumber());

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }


    @Test
    void whenEmployeeExists_findById_returnsTheEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        Optional<Employee> result = employeeService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(employee.getEmployeeId(), result.get().getEmployeeId());
    }

    @Test
    void whenEmployeeExists_deleteById_deletesTheEmployee() {
        // Arrange
        Employee employee = new Employee(1L, "maria", "0785212833", 2500, "proiectant");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        assertDoesNotThrow(() -> employeeService.deleteById(1L));

        // Assert
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void whenEmployeeDoesntExist_deleteById_throwsNotFoundException() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> employeeService.deleteById(1L));

        // Assert.
        assertEquals("The search with id 1 doesn't exist.", exception.getMessage());
        verify(employeeRepository).findById(1L);
        verify(employeeRepository, times(0)).deleteById(1L);
    }


}
