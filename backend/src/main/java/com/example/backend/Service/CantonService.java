package com.example.backend.Service;

import com.example.backend.DTO.CantonDto;

import java.util.List;

public interface CantonService {
    List<CantonDto> getAll();
    CantonDto getById(Long id);
}