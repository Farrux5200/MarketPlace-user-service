package com.company.userservice.service;

import com.company.userservice.dto.EmployeeDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.model.Employee;
import com.company.userservice.repository.EmployeeRepository;
import com.company.userservice.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
     private final EmployeeMapper employeeMapper;
     private final EmployeeRepository employeeRepository;


    public ResponseDto<EmployeeDto> create(EmployeeDto dto) {
        try {
            Employee employee=employeeMapper.toEntity(dto);
            employeeRepository.save(employee);
            return ResponseDto.<EmployeeDto>builder()
                    .code(0)
                    .success(true)
                    .message("Employee successful created!")
                    .data(this.employeeMapper.toDtoByNotUsers(employee))
                    .build();
        }catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .message(String.format("Employee saving error!"+e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<EmployeeDto> get(Integer employeeId) {
        return this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(employeeId)
                .map(employee -> ResponseDto.<EmployeeDto>builder()
                        .message("Employee successful get method!")
                        .success(true)
                        .data(this.employeeMapper.toDto(employee))
                        .build())
                .orElse(ResponseDto.<EmployeeDto>builder()
                        .code(-1)
                        .message("Employee id is not found!"+employeeId)
                        .build());
    }
    public ResponseDto<EmployeeDto> update(EmployeeDto dto, Integer employeeId) {
        try {
            return this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(employeeId)
                    .map(employee -> {
                        employeeMapper.update(employee, dto);
                        employeeRepository.save(employee);
                        return ResponseDto.<EmployeeDto>builder()
                                .success(true)
                                .message("Employee successful update!")
                                .data(this.employeeMapper.toDtoByNotUsers(employee))
                                .build();
                    })
                    .orElse(ResponseDto.<EmployeeDto>builder()
                            .code(-1)
                            .message("Employee id is not found!"+employeeId)
                            .build());

        }catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .code(-3)
                    .message(String.format("Employee updating error"+e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<EmployeeDto> delete(Integer employeeId) {
        try {
            return this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(employeeId)
                    .map(employee -> {
                        employee.setDeleteAt(LocalDateTime.now());
                        employeeRepository.save(employee);
                        return ResponseDto.<EmployeeDto>builder()
                                .success(true)
                                .message("Employee successful delete!")
                                .data(this.employeeMapper.toDtoByNotUsers(employee))
                                .build();
                    })
                    .orElse(ResponseDto.<EmployeeDto>builder()
                            .code(-1)
                            .message("Employee id is not found!"+employeeId)
                            .build());

        }catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .code(-3)
                    .message(String.format("Employee updating error"+e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<List<EmployeeDto>> getAll() {
        return ResponseDto.<List<EmployeeDto>>builder()
                .success(true)
                .message("Employee getAll method successful!")
                .data(employeeRepository.findAll()
                        .stream()
                        .map(employeeMapper::toDto).toList())
                .build();
    }
}
