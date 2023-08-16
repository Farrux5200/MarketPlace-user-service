package com.company.userservice.service;

import com.company.userservice.dto.EmployeeDto;
import com.company.userservice.dto.ErrorDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.service.mapper.UserMapper;
import com.company.userservice.service.validate.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserValidate userValidate;

    public ResponseDto<UserDto> create(UserDto dto) {
        List<ErrorDto> errors = this.userValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .message("Validate error ")
                    .errors(errors)
                    .build();
        }

        try {
            User user = this.userMapper.toEntity(dto);
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful create!")
                    .data(userMapper.toDto(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User while saving error" + e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<UserDto> get(Integer userId) {
        return this.userRepository.findByUserIdAndDeleteAtIsNull(userId)
                .map(user -> ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("User successful get method!")
                        .data(this.userMapper.toDtoWithFile(user))
                        .build())
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message(String.format("User id is not found!" + userId))
                        .build());
    }

    public ResponseDto<UserDto> update(UserDto dto, Integer userId) {
        try {
            return this.userRepository.findByUserIdAndDeleteAtIsNull(userId)
                    .map(user -> {
                        this.userMapper.update(user, dto);
                        this.userRepository.save(user);
                        return ResponseDto.<UserDto>builder()
                                .success(true)
                                .message("User Successful update")
                                .data(this.userMapper.toDto(user))
                                .build();
                    })
                    .orElse(ResponseDto.<UserDto>builder()
                            .code(-1)
                            .message(String.format("User id is not found!" + userId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User updating error!" + e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<UserDto> delete(Integer userId) {
        try {
            return this.userRepository.findByUserIdAndDeleteAtIsNull(userId)
                    .map(user -> {
                        user.setUpdateAt(LocalDateTime.now());
                        this.userRepository.save(user);
                        return ResponseDto.<UserDto>builder()
                                .success(true)
                                .message("User Successful delete!")
                                .data(this.userMapper.toDto(user))
                                .build();
                    })
                    .orElse(ResponseDto.<UserDto>builder()
                            .code(-1)
                            .message(String.format("User id is not found!" + userId))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User deleting error!" + e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<List<UserDto>> getAll() {
        return ResponseDto.<List<UserDto>>builder()
                .success(true)
                .message("User getAll method successful!")
                .data(this.userRepository.findAll().stream().map(userMapper::toDtoWithFile).toList())
                .build();
    }
}
