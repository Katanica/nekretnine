package com.example.backend.DTO;

import com.example.backend.Entity.City;
import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Enums.StatusType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CreateAdvertDto {
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

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @NotNull(message="Moraš unijeti tip oglasa")
    private AdvertType advertType;

    private Long cityId;

    private Float size;

    private List<String> imageUrls;
}