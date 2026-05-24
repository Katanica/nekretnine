package com.example.backend.Service;

import com.example.backend.DTO.CantonDto;
import com.example.backend.DTO.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getAll();
    List<CityDto> getByCanton(Long cantonId);
    CityDto getById(Long id);

    CityDto create(CityDto dto);

    CityDto update(CityDto dto);

    void delete(Long id);
}