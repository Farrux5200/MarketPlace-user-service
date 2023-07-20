package com.company.userservice.service;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public ResponseDto<UserDto> create(UserDto dto) {
        return null;
    }

    public ResponseDto<UserDto> get(Integer userId) {
        return null;
    }

    public ResponseDto<UserDto> update(UserDto dto, Integer userId) {
        return null;
    }

    public ResponseDto<UserDto> delete(Integer userId) {
        return null;
    }

    public ResponseDto<List<UserDto>> getAll() {
        return null;
    }
}
