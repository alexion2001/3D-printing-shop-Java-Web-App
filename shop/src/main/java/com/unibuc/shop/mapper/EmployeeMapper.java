package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.model.*;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee employeeRequestToEmployee(EmployeeRequest employeeRequest) {
        return new Employee(employeeRequest.getFullName(), employeeRequest.getMobileNumber(), employeeRequest.getSalary(), employeeRequest.getJob());
    }
}
