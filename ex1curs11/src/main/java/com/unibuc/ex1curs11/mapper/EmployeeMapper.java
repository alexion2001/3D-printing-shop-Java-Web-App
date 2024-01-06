package com.unibuc.ex1curs11.mapper;

import com.unibuc.ex1curs11.dto.*;
import com.unibuc.ex1curs11.model.*;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee employeeRequestToEmployee(EmployeeRequest employeeRequest) {
        return new Employee(employeeRequest.getFullName(), employeeRequest.getMobileNumber(), employeeRequest.getSalary(), employeeRequest.getJob());
    }
}
