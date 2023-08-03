package com.company.userservice;

import com.company.userservice.dto.EmployeeDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.model.Employee;
import com.company.userservice.repository.EmployeeRepository;
import com.company.userservice.service.EmployeeService;
import com.company.userservice.service.mapper.EmployeeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class EmployeeServiceApplicationTests {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void initMethod() {
        this.employeeMapper = mock(EmployeeMapper.class);
        this.employeeRepository = mock(EmployeeRepository.class);
        this.employeeService = new EmployeeService(employeeMapper, employeeRepository);
    }

    @Test
    void testEmployeeCreatePositive() {
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());


        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();

        when(this.employeeMapper.toDtoByNotUsers(any()))
                .thenReturn(employeeDto);
        ResponseDto<EmployeeDto> response = this.employeeService.create(employeeDto);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals("Employee successful created!", response.getMessage());

        verify(this.employeeRepository, times(1)).save(any());
        verify(this.employeeMapper, times(1)).toEntity(any());
    }

    @Test
    void testEmployeeCreateException() {
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());

        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();
        when(this.employeeMapper.toDtoByNotUsers(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<EmployeeDto> response = this.employeeService.create(employeeDto);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-3, response.getCode());
    }

    @Test
    void testEmployeeGetPositive() {
        Integer employeeId = 1;
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());
        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();
        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.of(Employee.builder().build()));
        when(this.employeeMapper.toDto(any()))
                .thenReturn(employeeDto);

        ResponseDto<EmployeeDto> response = this.employeeService.get(employeeId);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals("Employee successful get method!", response.getMessage());

        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());
        verify(this.employeeMapper, times(1)).toDto(any());

    }

    @Test
    void testEmployeeGetNegative() {
        Integer employeeId = 1;
        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<EmployeeDto> response = this.employeeService.get(employeeId);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-1, response.getCode());

        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());

    }

    @Test
    void testEmployeeUpdatePositive() {
        Integer employeeId = 1;
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());
        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();
        when(this.employeeMapper.toDtoByNotUsers(any()))
                .thenReturn(employeeDto);

        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.of(Employee.builder()
                        .employeesId(1)
                        .build()));

        ResponseDto<EmployeeDto> response = this.employeeService.update(employeeDto, employeeId);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals("Employee successful update!", response.getMessage());

        verify(this.employeeRepository, times(1)).save(any());
        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());
        verify(this.employeeMapper, times(1)).toDtoByNotUsers(any());
    }

    @Test
    void testEmployeeUpdateNegative() {
        Integer employeeId = 1;
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());
        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();
        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<EmployeeDto> response = this.employeeService.update(employeeDto, employeeId);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-1, response.getCode());

        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());

    }

    @Test
    void testEmployeeUpdateException() {
        Integer employeeId = 1;
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());
        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();
        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<EmployeeDto> response = this.employeeService.update(employeeDto, employeeId);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-3, response.getCode());

        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());
    }

    @Test
    void testEmployeeDeletePositive() {
        Integer employeeId = 1;
        List<UserDto> userDto = new ArrayList<>();
        userDto.add(UserDto.builder()
                .userId(1)
                .firsName("Farrux")
                .lastName("Izzatullayev")
                .phoneNumber("+998941865200")
                .middleName("Farrux")
                .userName("Farrux_boy_uzb")
                .build());
        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeesId(1)
                .users(userDto)
                .build();
        when(this.employeeMapper.toDtoByNotUsers(any()))
                .thenReturn(employeeDto);

        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.of(Employee.builder()
                        .employeesId(1)
                        .build()));

        ResponseDto<EmployeeDto> response = this.employeeService.delete(employeeId);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(0, response.getCode());
        Assertions.assertEquals("Employee successful delete!", response.getMessage());

        verify(this.employeeRepository, times(1)).save(any());
        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());
        verify(this.employeeMapper, times(1)).toDtoByNotUsers(any());
    }

    @Test
    void testEmployeeDeleteNegative() {
        Integer employeeId = 1;
        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<EmployeeDto> response = this.employeeService.delete(employeeId);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-1, response.getCode());

        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());

    }

    @Test
    void testEmployeeDeleteException() {
        Integer employeeId = 1;
        when(this.employeeRepository.findByEmployeesIdAndDeleteAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<EmployeeDto> response = this.employeeService.delete(employeeId);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(-3, response.getCode());

        verify(this.employeeRepository, times(1)).findByEmployeesIdAndDeleteAtIsNull(any());
    }

    @Test
    void testEmployeeGetAll() {
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder()
                .employeesId(1)
                .build());
    when(this.employeeRepository.findAll())
            .thenReturn(employees);
    ResponseDto<List<EmployeeDto>> response=this.employeeService.getAll();

    Assertions.assertTrue(response.isSuccess());
    Assertions.assertNotNull(response.getData());
    Assertions.assertEquals(0,response.getCode());
    Assertions.assertEquals("Employee getAll method successful!", response.getMessage());

    verify(this.employeeRepository, times(1)).findAll();
    }


}
