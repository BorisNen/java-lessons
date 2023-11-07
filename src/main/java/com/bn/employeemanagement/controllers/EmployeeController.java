package com.bn.employeemanagement.controllers;

import com.bn.employeemanagement.dto.EmployeeDto;
import com.bn.employeemanagement.models.Employee;
import com.bn.employeemanagement.services.EmployeeService;
import com.bn.employeemanagement.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MessageService messageService;

    @GetMapping("/fetch/employees")
    public List<Employee> fetchEmployees() {
        return employeeService.getAllEmployees();
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
}
