package com.company.userservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto <T> {
    private int code;
    private String message;
    private boolean success;
    private T data;
    private List<ErrorDto> errors;
}
