package com.example.backend.Service;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.Repository.AgencyProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AgencyProfileService {

    List<AgencyProfileDto> getAll();

    AgencyProfileDto getById(Long id);

    AgencyProfileDto create(AgencyProfileDto dto);

    AgencyProfileDto update(AgencyProfileDto dto);

    void delete(Long id);
}
