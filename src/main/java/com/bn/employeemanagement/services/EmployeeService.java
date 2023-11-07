package com.bn.employeemanagement.services;

import com.bn.employeemanagement.dto.EmployeeDto;
import com.bn.employeemanagement.mappers.EmployeeMapper;
import com.bn.employeemanagement.models.Employee;
import com.bn.employeemanagement.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
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
