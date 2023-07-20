package com.company.userservice.controller;

import com.company.userservice.dto.EmployeeDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(
            tags = "Magazine Market Place Create ",
            summary = "Your summary create method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeeDto.class
                            )
                    )
            )
    )
    @PostMapping(value = "/create")
    public ResponseDto<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        return this.employeeService.create(dto);
    }


    @Operation(
            tags = "Magazine Market Place Get ",
            summary = "Your summary get method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeeDto.class
                            )
                    )
            )
    )
    @GetMapping("/get/{id}")
    public ResponseDto<EmployeeDto> get(@PathVariable("id") Integer employeeId) {
        return this.employeeService.get(employeeId);
    }


    @Operation(
            tags = "Magazine Market Place Update ",
            summary = "Your summary update method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeeDto.class
                            )
                    )
            )
    )
    @PutMapping("/update/{id}")
    public ResponseDto<EmployeeDto> update(@RequestBody EmployeeDto dto,
                                            @PathVariable("id") Integer employeeId) {
        return this.employeeService.update(dto, employeeId);
    }


    @Operation(
            tags = "Magazine Market Place Delete ",
            summary = "Your summary delete method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeeDto.class
                            )
                    )
            )
    )
    @DeleteMapping("/delete/{id}")
    public ResponseDto<EmployeeDto> delete(@PathVariable("id") Integer employeeId) {
        return this.employeeService.delete(employeeId);
    }

    @Operation(
            tags = "Magazine Market Place GetAll ",
            summary = "Your summary getAll method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeeDto.class
                            )
                    )
            )
    )
    @GetMapping("/get-all")
    public ResponseDto<List<EmployeeDto>> getAll() {
        return this.employeeService.getAll();
    }

}
