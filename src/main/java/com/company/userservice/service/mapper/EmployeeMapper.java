package com.company.userservice.service.mapper;

import com.company.userservice.dto.EmployeeDto;
import com.company.userservice.model.Employee;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Autowired
    protected UserMapper userMapper;

     @Mapping(target = "createAt", ignore = true)
     @Mapping(target = "updateAt", ignore = true)
     @Mapping(target = "deleteAt", ignore = true)
     @Mapping(target = "employeesId", ignore = true)
    public abstract Employee toEntity(EmployeeDto dto);


     @Mapping(target = "users", expression = "java(employee.getUsers().stream().map(userMapper::toDto).toList())")
    public abstract EmployeeDto toDto(Employee employee);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @Mapping(target = "users", ignore = true)
     public abstract EmployeeDto toDtoByNotUsers(Employee employee);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @Mapping(target = "employeesId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Employee employee, EmployeeDto dto);
}
