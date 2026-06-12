package com.example.backend.DTO;

import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto {
    private Long id;
    @NotNull(message="Moraš unijeti tip nekretnine")
    private PropertyType propertyType;

    @NotBlank(message="Moraš unijeti naslov")
    private String title;
    private String description;

    @NotNull(message="Moraš unijeti cijenu")
    private Double price;

    private LocalDateTime postedAt;
    private LocalDateTime updatedAt;

    @NotNull(message="Moraš unijeti tip oglasa")
    private AdvertType advertType;

    private Long cityId;
    private String cityName;

    private Float size;
    
    private List<String> imageUrls;
}