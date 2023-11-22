package com.bn.employeemanagement.controllers;

import com.bn.employeemanagement.models.Employee;
import com.bn.employeemanagement.services.EmployeeService;
import com.bn.employeemanagement.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void pageEmployeesDefaultValuesOkTest() throws Exception {
        when(employeeService.getAllEmployees(any())).thenReturn(getEmployeePage());
        mockMvc.perform(get("/page/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    private Page<Employee> getEmployeePage() {
        Pageable pageable = PageRequest.of(0, 5);
        return new PageImpl<Employee>(List.of(new Employee()), pageable, 1L);
    }
}
