package com.company.userservice.repository;

import com.company.userservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   Optional<Employee> findByEmployeesIdAndDeleteAtIsNull(Integer employeeId);
}
