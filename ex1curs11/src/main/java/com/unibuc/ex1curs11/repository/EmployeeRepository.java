package com.unibuc.ex1curs11.repository;

import com.unibuc.ex1curs11.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFullName(String fullName);
}
