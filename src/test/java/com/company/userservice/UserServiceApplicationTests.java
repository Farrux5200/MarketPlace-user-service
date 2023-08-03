package com.company.userservice;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.service.UserService;
import com.company.userservice.service.mapper.UserMapper;
import com.company.userservice.service.validate.UserValidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserServiceApplicationTests {


    private UserService userService;
    private UserMapper userMapper;
    private UserRepository userRepository;

    @BeforeEach
    void initMethod() {
        this.userMapper = mock(UserMapper.class);
        this.userRepository = mock(UserRepository.class);
        this.userService = new UserService(userMapper, userRepository);
    }

    @Test
    void testCreatePositive() {
        UserDto userDto = UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build();

        when(this.userMapper.toDto(any()))
                .thenReturn(userDto);

        ResponseDto<UserDto> response = this.userService.create(userDto);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals("User successful create!", response.getMessage());

        verify(this.userRepository, times(1)).save(any());
        verify(this.userMapper, times(1)).toEntity(any());
        verify(this.userMapper, times(1)).toDto(any());
    }

    @Test
    void testCreateExceptions() {
        UserDto userDto = UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build();
        when(this.userMapper.toDto(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<UserDto> response = this.userService.create(userDto);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(-3, response.getCode());

        verify(this.userMapper, times(1)).toDto(any());
    }

    @Test
    void testGetPositive() {
        Integer userId = 1;
        UserDto userDto = UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build();
        when(this.userMapper.toDto(any()))
                .thenReturn(userDto);
        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.of(User.builder()
                        .userId(1)
                        .firsName("Farrux")
                        .lastName("Izzatullayev")
                        .phoneNumber("+998941865200")
                        .middleName("Farrux")
                        .userName("Farrux_boy_uzb")
                        .build()));

        ResponseDto<UserDto> response = this.userService.get(userId);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals("User successful get method!", response.getMessage());
        Assertions.assertNotNull(response.getData());


        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());
        verify(this.userMapper, times(1)).toDto(any());

    }

    @Test
    void testGetNegative() {
        Integer userId = 1;

        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<UserDto> response = this.userService.get(userId);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-1, response.getCode());

        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());
    }

    @Test
    void testUpdatePositive() {
        Integer userId = 1;
        UserDto userDto = UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build();

        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.of(User.builder()
                        .userId(1)
                        .firsName("Farrux")
                        .lastName("Izzatullayev")
                        .phoneNumber("+998941865200")
                        .middleName("Farrux")
                        .userName("Farrux_boy_uzb")
                        .build()));

        when(this.userMapper.toDto(any()))
                .thenReturn(userDto);

        ResponseDto<UserDto> response = this.userService.update(userDto, userId);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals("User Successful update", response.getMessage());
        Assertions.assertEquals(0, response.getCode());

        verify(this.userRepository, times(1)).save(any());
        verify(this.userMapper, times(1)).toDto(any());
        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());
    }

    @Test
    void testUpdateNegative() {
        Integer userId = 1;
        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<UserDto> response = this.userService.update(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build(), userId);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-1, response.getCode());

        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());
    }

    @Test
    void testUpdateException() {
        Integer userId = 1;

        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<UserDto> response = this.userService.update(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build(), userId);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(-3, response.getCode());

        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());
    }

    @Test
    void testDeletePositive() {
        Integer userId = 1;
        UserDto userDto = UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build();
        when(this.userRepository.findByUserIdAndDeleteAtIsNull(userId))
                .thenReturn(Optional.of(User.builder()
                        .userId(1)
                        .firsName("Farrux")
                        .lastName("Izzatullayev")
                        .phoneNumber("+998941865200")
                        .middleName("Farrux")
                        .userName("Farrux_boy_uzb")
                        .build()));
        when(this.userMapper.toDto(any()))
                .thenReturn(userDto);
        ResponseDto<UserDto> response = this.userService.delete(userId);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals("User Successful delete!", response.getMessage());
        Assertions.assertEquals(0, response.getCode());

        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());
        verify(this.userMapper, times(1)).toDto(any());

    }

    @Test
    void testDeleteNegative() {
        Integer userId = 1;
        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<UserDto> response = this.userService.delete(userId);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-1, response.getCode());
    }

    @Test
    void testDeleteException() {
        Integer userId = 1;
        when(this.userRepository.findByUserIdAndDeleteAtIsNull(any()))
                .thenThrow(RuntimeException.class);
        ResponseDto<UserDto> response = this.userService.delete(userId);
        Assertions.assertEquals(-3, response.getCode());
        Assertions.assertFalse(response.isSuccess());
        //Assertions.assertEquals("User deleting error!", response.getMessage());
        verify(this.userRepository, times(1)).findByUserIdAndDeleteAtIsNull(any());

    }

    @Test
    void testGetAll() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());
        when(this.userRepository.findAll())
                .thenReturn(users);

        ResponseDto<List<UserDto>> response = this.userService.getAll();

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("User getAll method successful!", response.getMessage());

        verify(this.userRepository, times(1)).findAll();
    }

}
