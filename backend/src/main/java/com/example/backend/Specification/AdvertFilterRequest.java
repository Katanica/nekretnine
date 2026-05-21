package com.example.backend.Specification;

import com.example.backend.Entity.City;
import com.example.backend.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Getter
@Setter
public class AdvertFilterRequest {
    String title;
    PropertyType propertyType;
    Double maxPrice;
    Double minPrice;
    Integer cityId;
    Integer cantonId;
    Float maxSize;
    Float minSize;
}
