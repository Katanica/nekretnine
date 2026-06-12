package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String contentType;
    private LocalDateTime uploadedAt;
    private String avatarUrl;
}

