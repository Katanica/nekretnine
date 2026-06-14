package com.example.backend.Specification;

import com.example.backend.Entity.City;
import com.example.backend.Enums.AdvertType;
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
    private String title;
    private PropertyType propertyType;
    private AdvertType advertType;
    private Double maxPrice;
    private Double minPrice;
    private Long cityId;
    private Long cantonId;
    private Float maxSize;
    private Float minSize;
}
