package com.example.backend.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {
    private Long id;
    private AdvertDto advert;
    private String slikaUrl;
    private Boolean isPrimary = false;
    private LocalDateTime uploadedAt;

}
