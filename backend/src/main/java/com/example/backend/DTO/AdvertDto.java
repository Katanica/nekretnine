package com.example.backend.DTO;

import com.example.backend.Entity.City;
import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message="Moraš unijeti tip nekretnine")
    private PropertyType propertyType;

    @NotBlank(message="Moraš unijeti naslov")
    private String title;
    private String description;

    @NotBlank(message="Moraš unijeti cijenu")
    private Double price;

    private LocalDateTime postedAt;
    private LocalDateTime updatedAt;

    @NotBlank(message="Moraš unijeti tip oglasa")
    private AdvertType advertType;
    //private City city;
    private Float size;
}
