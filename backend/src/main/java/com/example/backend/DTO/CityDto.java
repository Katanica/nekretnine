package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private Long id;

    @NotBlank(message = "Morate unijeti naziv grada")
    private String name;

    private CantonDto canton;
}