package com.bn.employeemanagement.controllers;

import com.bn.employeemanagement.dto.EmployeeDto;
import com.bn.employeemanagement.models.Employee;
import com.bn.employeemanagement.services.EmployeeService;
import com.bn.employeemanagement.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MessageService messageService;

    @GetMapping("/fetch/employees")
    public List<Employee> fetchEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/page/employees")
    public ResponseEntity<Map<String, Object>> fetchEmployees(
            @RequestParam(required = false, defaultValue = "1") int currentPage,
            @RequestParam(required = false, defaultValue = "5") int perPage
    ) {
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<Employee> page = employeeService.getAllEmployees(pageable);
        Map<String, Object> response = Map.of(
                "employees", page.getContent(),
                "totalPages", page.getTotalPages(),
                "totalElements", page.getTotalElements()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/message")
    public String getMessage(@RequestParam String msg) {
        return messageService.getMessage(msg);
    }

    @PostMapping("/save/employees")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto dto) {
        Employee savedInDb = employeeService.saveEmployee(dto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }

    @PutMapping("/update/employees")
    public ResponseEntity<?> updateEmployee(
            @RequestParam Long depId,
            @RequestParam Integer employeeNum
    ) {
        employeeService.updateEmployeeDepartment(depId, employeeNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/employees/native")
    public ResponseEntity<?> updateEmployeeNative(
            @RequestParam Long depId,
            @RequestParam Integer employeeNum
    ) {
        employeeService.updateEmployeeDepartmentNative(depId, employeeNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
