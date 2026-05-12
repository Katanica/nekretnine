package com.example.backend.ServiceImpl;

import com.example.backend.DTO.CantonDto;
import com.example.backend.Entity.Canton;
import com.example.backend.Mapper.CantonMapper;
import com.example.backend.Repository.CantonRepository;
import com.example.backend.Service.CantonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CantonServiceImpl implements CantonService {

    private final CantonRepository cantonRepository;
    private final CantonMapper cantonMapper;

    @Override
    public List<CantonDto> getAll() {
        return cantonRepository.findAll()
                .stream()
                .map(cantonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CantonDto getById(Long id) {
        Canton canton = cantonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canton not found"));
        return cantonMapper.toDto(canton);
    }
}