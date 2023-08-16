package com.company.userservice.controller;

import com.company.userservice.client.service.FileClient;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;
    @Operation(
            tags = "Magazine Market Place Create ",
            summary = "Your summary create method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @PostMapping("/create")
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.create(dto);
    }


    @Operation(
            tags = "Magazine Market Place Get ",
            summary = "Your summary get method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @GetMapping("/get/{id}")
    public ResponseDto<UserDto> get(@PathVariable("id") Integer userId) {
        return this.userService.get(userId);
    }


    @Operation(
            tags = "Magazine Market Place Update ",
            summary = "Your summary update method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @PutMapping("/update/{id}")
    public ResponseDto<UserDto> update(@RequestBody UserDto dto,
                                       @PathVariable("id") Integer userId) {
        return this.userService.update(dto, userId);
    }

    @Operation(
            tags = "Magazine Market Place Delete ",
            summary = "Your summary delete method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @DeleteMapping("/delete/{id}")
    public ResponseDto<UserDto> delete(@PathVariable("id") Integer userId) {
        return this.userService.delete(userId);
    }


    @Operation(
            tags = "Magazine Market Place GetAll ",
            summary = "Your summary getAll method",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @GetMapping("/get-all")
    public ResponseDto<List<UserDto>> getAll() {
        return this.userService.getAll();
    }

}
