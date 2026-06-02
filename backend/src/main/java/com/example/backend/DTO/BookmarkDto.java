package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDto {
    private Long id;
    private AdvertDto advert;
    private ProfileDto profile;
    private LocalDateTime createdAt;
}
