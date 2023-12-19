package com.bn.employeemanagement.services;

import com.bn.employeemanagement.dto.EmployeeDto;
import com.bn.employeemanagement.mappers.DepartmentRepo;
import com.bn.employeemanagement.mappers.EmployeeMapper;
import com.bn.employeemanagement.models.Department;
import com.bn.employeemanagement.models.Employee;
import com.bn.employeemanagement.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees() {
        System.out.println("\u001B[31m" + " " + Thread.currentThread().getName());
        return employeeRepo.findAll();
    }

    public List<Department> getAllDepartments() {
        System.out.println("\u001B[32m" + " " + Thread.currentThread().getName());
        return departmentRepo.findAll();
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }

    @Transactional
    public void updateEmployeeDepartment(Long depId, Integer employeeNum) {
        Department department = departmentRepo.findById(depId).get();
        employeeRepo.updateDepartment(department, employeeNum);
    }

    @Transactional
    public void updateEmployeeDepartmentNative(Long depId, Integer employeeNum) {
        employeeRepo.updateDepartmentNative(depId, employeeNum);
    }

    public Employee saveEmployee(EmployeeDto dto) {
        Optional<Employee> dbObject = employeeRepo.findEmployeeByEmployeeNum(dto.employeeNum());
        Long id;
        if(dbObject.isPresent()) {
            id = dbObject.get().getId();
            log.info("Updating Employee with id {}", id);
        } else {
            id = null;
            log.info("Inserting new Employee");
        }
        Employee employee = employeeMapper.convertDtoToEntity(dto, id);
        return employeeRepo.saveAndFlush(employee);
    }
}
