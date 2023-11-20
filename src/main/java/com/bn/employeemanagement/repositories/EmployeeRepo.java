package com.bn.employeemanagement.repositories;

import com.bn.employeemanagement.models.Department;
import com.bn.employeemanagement.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    boolean existsEmployeeByEmployeeNum(Integer num);

    Optional<Employee> findEmployeeByEmployeeNum(Integer num);

    @Modifying
    @Query("UPDATE Employee SET department = :department WHERE employeeNum = :employeeNum")
    void updateDepartment(Department department, Integer employeeNum);

    @Modifying
    @Query(value = "UPDATE employee SET department_id = :depId WHERE employee_number = :employeeNum", nativeQuery = true)
    void updateDepartmentNative(Long depId, Integer employeeNum);
}
