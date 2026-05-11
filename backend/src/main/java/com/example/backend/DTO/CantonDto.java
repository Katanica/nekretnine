package com.example.backend.DTO;

import com.example.backend.Entity.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CantonDto {
    private Long id;
    private String name;
    private List<CityDto> cities = new ArrayList<>();
}
