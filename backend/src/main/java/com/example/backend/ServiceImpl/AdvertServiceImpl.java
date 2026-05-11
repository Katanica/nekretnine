package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Mapper.AdvertMapper;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Service.AdvertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository repository;
    private final AdvertMapper mapper;

    @Override
    public List<AdvertDto> getAll(){
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());

    }

    @Override
    public AdvertDto getById(Long id){
        Advert advert = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDto(advert);
    }

    @Override
    public AdvertDto create(AdvertDto dto){
        Advert advert = mapper.toEntity(dto);
        Advert saved = repository.save(advert);
        return mapper.toDto(saved);
    }

    @Override
    public AdvertDto update(AdvertDto dto){
        Advert existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        mapper.updateEntityFromDto(dto, existing);
        Advert saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }
}
