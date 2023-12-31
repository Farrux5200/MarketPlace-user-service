package com.company.userservice.dto;

import com.company.userservice.client.dto.FileModelDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer userId;

    private Integer employeeId;

    private String  firsName;
    private String lastName;
    private String middleName;
    private String userName;
    private String  borrowName;
    private String phoneNumber;
    private String passportSeries;
    private String firstAddress;
    private String secondAddress;
    private Double monthlyPrice;

    private FileModelDto file;

    private LocalDate birthDate;
    private LocalDate workingDate;
    private LocalDate workingDays;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
