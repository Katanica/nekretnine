package com.example.backend.ServiceImpl;

import com.example.backend.DTO.CantonDto;
import com.example.backend.Entity.Canton;
import com.example.backend.Mapper.CantonMapper;
import com.example.backend.Repository.CantonRepository;
import com.example.backend.Service.CantonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CantonServiceImpl implements CantonService {

    private final CantonRepository repository;
    private final CantonMapper cantonMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CantonDto create(CantonDto dto){
        Canton canton = cantonMapper.toEntity(dto);
        Canton saved = repository.save(canton);
        return cantonMapper.toDto(saved);
    }

    @Override
    public List<CantonDto> getAll() {
        return repository.findAll()
                .stream()
                .map(cantonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CantonDto> getAllWithCities() {
        return repository.findAll()
                .stream()
                .map(cantonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CantonDto getById(Long id) {
        Canton canton = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canton not found"));
        return cantonMapper.toDto(canton);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id){
        repository.deleteById(id);
    }
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CantonDto update(CantonDto dto){
        Canton existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Canton not found"));
        cantonMapper.updateEntityFromDto(dto, existing);
        Canton saved = repository.save(existing);
        return cantonMapper.toDto(saved);
    }
}