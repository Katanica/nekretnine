package com.example.backend.Service;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdvertService {

    List<AdvertDto> getAll();

    AdvertDto getById(Long id);

    AdvertDto create(AdvertDto dto);

    AdvertDto update(AdvertDto dto);

    void delete(Long id);
}
