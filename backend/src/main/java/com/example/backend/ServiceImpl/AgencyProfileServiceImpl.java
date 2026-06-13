package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.DTO.CreateAgencyProfileDto;
import com.example.backend.DTO.CreateUserProfileDto;
import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Entity.AgencyProfile;
import com.example.backend.Entity.City;
import com.example.backend.Entity.Profile;
import com.example.backend.Entity.UserProfile;
import com.example.backend.Enums.Role;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Mapper.AgencyProfileMapper;
import com.example.backend.Repository.AgencyProfileRepository;
import com.example.backend.Repository.CityRepository;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Service.AgencyProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgencyProfileServiceImpl implements AgencyProfileService {

    private final AgencyProfileRepository repository;
    private final ProfileRepository profileRepository;
    private final CityRepository cityRepository;

    @Qualifier("agencyProfileMapper")
    private final AgencyProfileMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AgencyProfileDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgencyProfileDto getById(Long id) {
        AgencyProfile agencyProfile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AgencyProfile nije pronađen: " + id));
        return mapper.toDto(agencyProfile);
    }

    @Override
    public AgencyProfileDto create(@Valid CreateAgencyProfileDto createDto){
        City city = cityRepository.findById(createDto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Grad nije pronađen: " + createDto.getCityId()));

        AgencyProfileDto dto = new AgencyProfileDto();

        dto.setAgencyName(createDto.getAgencyName());
        dto.setOib(createDto.getOib());
        dto.setEmail(createDto.getEmail());
        dto.setStatus(createDto.getStatus());
        dto.setPassword(createDto.getPassword());
        dto.setPhone(createDto.getPhone());
        dto.setAdverts(createDto.getAdverts());
        dto.setCreatedAt(createDto.getCreatedAt());
        dto.setAvatarUrl(createDto.getAvatarUrl());

        AgencyProfile agencyProfile = mapper.toEntity(dto);
        agencyProfile.setCity(city);
        agencyProfile.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        agencyProfile.setRole(Role.USER);

        AgencyProfile saved = repository.save(agencyProfile);
        return mapper.toDto(saved);
    }

    @Override
    public AgencyProfileDto create(@Valid @RequestBody AgencyProfileDto dto) {
        AgencyProfile agencyProfile = mapper.toEntity(dto);
        agencyProfile.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        agencyProfile.setRole(Role.AGENCY);   // <-- bitno, bez ovoga login puca
        AgencyProfile saved = repository.save(agencyProfile);
        return mapper.toDto(saved);
    }

    public boolean isOwner(Long profileId, String email) {
        return profileRepository.findById(profileId)
                .map(Profile::getEmail)
                .map(profileEmail -> Objects.equals(profileEmail, email))
                .orElse(false);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @agencyProfileServiceImpl.isOwner(#dto.id, authentication.name)")
    public AgencyProfileDto update(@Valid @RequestBody AgencyProfileDto dto) {
        AgencyProfile existing = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("AgencyProfile nije pronađen: " + dto.getId()));

        existing.setUserName(dto.getUserName());
        existing.setAgencyName(dto.getAgencyName());
        existing.setEmail(dto.getEmail());
        existing.setStatus(dto.getStatus());
        existing.setPhone(dto.getPhone());
        AgencyProfile saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @agencyProfileServiceImpl.isOwner(#id, authentication.name)")
    public void delete(Long id) {
        repository.deleteById(id);
    }
}