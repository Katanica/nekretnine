package com.example.backend.DTO;


import java.util.ArrayList;
import java.util.List;

import com.example.backend.Entity.Canton;
import jakarta.persistence.*;
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
    private String name;
    private Canton canton;
    private List<AdvertDto> adverts = new ArrayList<>();
    private List<ProfileDto> profiles = new ArrayList<>();
}
