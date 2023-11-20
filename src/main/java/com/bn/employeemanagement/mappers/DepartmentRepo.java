package com.bn.employeemanagement.mappers;

import com.bn.employeemanagement.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
