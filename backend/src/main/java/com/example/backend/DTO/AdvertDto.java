package com.example.backend.DTO;

import com.example.backend.Entity.City;
import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto {
    private Long id;
    private PropertyType propertyType;
    private String title;
    private String description;
    private Double price;
    private LocalDateTime postedAt;
    private LocalDateTime updatedAt;
    private AdvertType advertType;
    private City city;
    private Float size;
}
