package com.bn.employeemanagement.repositories;

import com.bn.employeemanagement.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({
        "/sql/employee_data.sql"
})
class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    void testFindEmployeeByEmployeeNumSuccess() {
        Optional<Employee> result = employeeRepo.findEmployeeByEmployeeNum(123);
        assertThat(result.get().getFirstName())
                .isNotNull()
                .isEqualTo("John");
    }
}
