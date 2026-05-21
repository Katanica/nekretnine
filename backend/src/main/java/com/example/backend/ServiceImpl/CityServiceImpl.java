package com.example.backend.ServiceImpl;

import com.example.backend.DTO.CityDto;
import com.example.backend.Entity.City;
import com.example.backend.Mapper.CityMapper;
import com.example.backend.Repository.CityRepository;
import com.example.backend.Service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> getAll() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getByCanton(Long cantonId) {
        return cityRepository.findByCantonId(cantonId)
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto getById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return cityMapper.toDto(city);
    }
}