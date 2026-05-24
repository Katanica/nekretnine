package com.example.backend.ServiceImpl;

import com.example.backend.DTO.CantonDto;
import com.example.backend.DTO.CityDto;
import com.example.backend.Entity.Canton;
import com.example.backend.Entity.City;
import com.example.backend.Mapper.CityMapper;
import com.example.backend.Repository.CityRepository;
import com.example.backend.Service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final CityMapper cityMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CityDto create(CityDto dto){
        City city = cityMapper.toEntity(dto);
        City saved = repository.save(city);
        return cityMapper.toDto(saved);
    }

    @Override
    public List<CityDto> getAll() {
        return repository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getByCanton(Long cantonId) {
        return repository.findByCantonId(cantonId)
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto getById(Long id) {
        City city = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return cityMapper.toDto(city);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id){
        repository.deleteById(id);
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CityDto update(CityDto dto){
        City existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("City not found"));
        cityMapper.updateEntityFromDto(dto, existing);
        City saved = repository.save(existing);
        return cityMapper.toDto(saved);
    }
}