package com.example.backend.Service;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.DTO.CreateAgencyProfileDto;
import com.example.backend.Repository.AgencyProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AgencyProfileService {

    List<AgencyProfileDto> getAll();

    AgencyProfileDto getById(Long id);

    AgencyProfileDto create(AgencyProfileDto dto);
    AgencyProfileDto create(CreateAgencyProfileDto dto);

    AgencyProfileDto update(CreateAgencyProfileDto dto);

    void delete(Long id);
}
