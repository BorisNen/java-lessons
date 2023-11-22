package com.bn.employeemanagement.mappers;

import com.bn.employeemanagement.dto.EmployeeDto;
import com.bn.employeemanagement.models.Employee;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeMapperTest {

    private final EmployeeMapper underTest = Mappers.getMapper(EmployeeMapper.class);

    @ParameterizedTest
    @MethodSource("paramProvider")
    void convertDtoToEntityTest(EmployeeDto dto, String[] emptyFields) {
        Employee result = underTest.convertDtoToEntity(dto, 1L);
        assertThat(result)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept(emptyFields);
    }

    private static Stream<Arguments> paramProvider() {
        return Stream.of(
                Arguments.of(
                        new EmployeeDto("John", null, null, null),
                        new String[]{"lastName", "employeeNum", "cityId", "department", "projects"}
                ),
                Arguments.of(
                        new EmployeeDto(null, "Travolta", null, null),
                        new String[]{"firstName", "employeeNum", "cityId", "department", "projects"}
                ),
                Arguments.of(
                        new EmployeeDto(null, null, 123, null),
                        new String[]{"lastName", "firstName", "cityId", "department", "projects"}
                )
        );
    }
}
