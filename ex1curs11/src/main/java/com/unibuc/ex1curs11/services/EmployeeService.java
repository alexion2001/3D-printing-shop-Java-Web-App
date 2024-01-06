package com.unibuc.ex1curs11.services;

import com.unibuc.ex1curs11.exception.DuplicateException;
import com.unibuc.ex1curs11.model.Employee;
import com.unibuc.ex1curs11.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    public Employee create(Employee employee) {
        Optional<Employee> existingEmployeeSameName = employeeRepository.findByFullName(employee.getFullName());
        existingEmployeeSameName.ifPresent(e -> {
            throw new DuplicateException();
        });
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }
}
