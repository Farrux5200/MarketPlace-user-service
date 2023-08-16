package com.company.userservice.client.service;

import com.company.userservice.client.dto.FileModelDto;
import com.company.userservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "file-service", path = "file-service/file")
public interface FileClient {

    @GetMapping("/download/{id}")
    ResponseDto<FileModelDto> download(@PathVariable("id") Integer fileId);

}
