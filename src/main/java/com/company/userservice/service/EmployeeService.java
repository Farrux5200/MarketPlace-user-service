package com.company.userservice.service;

import com.company.userservice.dto.EmployeeDto;
import com.company.userservice.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    public ResponseDto<EmployeeDto> create(EmployeeDto dto) {
        return null;
    }

    public ResponseDto<EmployeeDto> get(Integer employeeId) {
        return null;
    }

    public ResponseDto<EmployeeDto> update(EmployeeDto dto, Integer employeeId) {
        return null;
    }

    public ResponseDto<EmployeeDto> delete(Integer employeeId) {
        return null;
    }

    public ResponseDto<List<EmployeeDto>> getAll() {
        return null;
    }
}
