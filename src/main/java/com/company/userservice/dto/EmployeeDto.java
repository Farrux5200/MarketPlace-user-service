package com.company.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    private Integer employeesId;
    private List<UserDto> users;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
