package com.example.backend.Service;
import com.example.backend.DTO.CityDto;
import com.example.backend.Entity.City;
import com.example.backend.Repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<CityDto> findAll();
    City findById(Long id);
    List<CityDto> getByCanton(Long cantonId);

}
