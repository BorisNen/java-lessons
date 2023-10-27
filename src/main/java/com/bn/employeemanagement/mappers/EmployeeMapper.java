package com.bn.employeemanagement.mappers;

import com.bn.employeemanagement.dto.EmployeeDto;
import com.bn.employeemanagement.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "employeeNum", source = "dto.employeeNum")
    Employee convertDtoToEntity(EmployeeDto dto, Long id);
}
