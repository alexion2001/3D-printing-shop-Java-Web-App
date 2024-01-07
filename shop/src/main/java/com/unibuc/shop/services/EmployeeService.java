package com.unibuc.shop.services;

import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
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

    public Employee updateEmployee(Long id, Employee employee)
    {
        Optional<Employee> existingEmployeeSameName = employeeRepository.findByFullName(employee.getFullName());

        Optional<Employee> existingEmployee = employeeRepository.findById(id.longValue());
        if(existingEmployee.isPresent()){
            Employee emp =  existingEmployee.get();
            emp.setJob(employee.getJob());
            emp.setMobileNumber(employee.getMobileNumber());
            emp.setSalary(employee.getSalary());
            return employeeRepository.save(emp);
        }else
        {
            throw new NotFoundException(id.longValue());
        }
    }

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }
    public Optional<Employee> findByFullName(String name) {
        return employeeRepository.findByFullName(name);
    }
}
