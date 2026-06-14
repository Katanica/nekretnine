package com.example.backend.Service;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.DTO.CantonDto;

import java.util.List;

public interface CantonService {
    List<CantonDto> getAll();

    List<CantonDto> getAllWithCities();

    CantonDto getById(Long id);

    CantonDto create(CantonDto dto);

    CantonDto update(CantonDto dto);

    void delete(Long id);
}