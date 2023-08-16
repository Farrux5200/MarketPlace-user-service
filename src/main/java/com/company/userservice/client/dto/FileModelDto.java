package com.company.userservice.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileModelDto {
    private Integer fileId;
    private String fileName;
    private String filePath;
    private String ext;
    private byte[] data;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
