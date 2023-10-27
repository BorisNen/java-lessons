package com.bn.employeemanagement.repositories;

import com.bn.employeemanagement.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    boolean existsEmployeeByEmployeeNum(Integer num);

    Optional<Employee> findEmployeeByEmployeeNum(Integer num);
}
