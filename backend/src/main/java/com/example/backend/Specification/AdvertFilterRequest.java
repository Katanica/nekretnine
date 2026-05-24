package com.example.backend.Specification;

import com.example.backend.Entity.City;
import com.example.backend.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdvertFilterRequest {
    String title;
    PropertyType propertyType;
    Double maxPrice;
    Double minPrice;
    Long cityId;
    Long cantonId;
    Float maxSize;
    Float minSize;
}
