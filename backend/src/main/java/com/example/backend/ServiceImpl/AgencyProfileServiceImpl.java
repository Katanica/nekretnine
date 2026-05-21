package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.Entity.AgencyProfile;
import com.example.backend.Mapper.AgencyProfileMapper;
import com.example.backend.Repository.AgencyProfileRepository;
import com.example.backend.Service.AgencyProfileService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class AgencyProfileServiceImpl implements AgencyProfileService {

    public final AgencyProfileRepository repository;
    @Qualifier("agencyProfileMapper")
    public final AgencyProfileMapper mapper;

    @Override
    public List<AgencyProfileDto> getAll(){
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AgencyProfileDto getById(Long id){
        AgencyProfile agencyProfile = repository.findById(id).orElseThrow(() -> new RuntimeException("Agency not found"));
        return mapper.toDto(agencyProfile);
    }

    @Override
    public AgencyProfileDto create(AgencyProfileDto dto){
        AgencyProfile agencyProfile = mapper.toEntity(dto);
        AgencyProfile saved = repository.save(agencyProfile);
        return mapper.toDto(saved);

    }

    @Override
    public AgencyProfileDto update(AgencyProfileDto dto){
        AgencyProfile existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Agency not found"));

        existing.setUserName(dto.getUserName());
        existing.setAgencyName(dto.getAgencyName());
        existing.setEmail(dto.getEmail());
        existing.setStatus(dto.getStatus());
        existing.setPhone(dto.getPhone());
        AgencyProfile saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }
}
