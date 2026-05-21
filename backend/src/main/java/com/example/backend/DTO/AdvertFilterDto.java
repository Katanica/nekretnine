package com.example.backend.DTO;

import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdvertFilterDto {
    private String title;
    private PropertyType propertyType;
    private Double minPrice;
    private Double maxPrice;
    private AdvertType advertType;
    private Long cityId;
    private Float minSize;
    private Float maxSize;
}
