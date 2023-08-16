package com.company.userservice.controller;

import com.company.userservice.client.dto.FileModelDto;
import com.company.userservice.client.service.FileClient;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.TestResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final Environment environment;
    private final FileClient fileClient;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/download/{id}")
    public ResponseDto<FileModelDto> getFile(@PathVariable("id") Integer fileId){
        return this.fileClient.download(fileId);
    }

    @GetMapping("/get")
    public TestResponse getValue(HttpServletRequest request, HttpServletResponse response) {

        log.info(String.format("User-service port :: %s", serverPort));
        return new TestResponse(environment.getProperty("server.port", Integer.class));
    }
}
