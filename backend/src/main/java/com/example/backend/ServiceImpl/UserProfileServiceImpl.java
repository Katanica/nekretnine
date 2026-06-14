package com.example.backend.ServiceImpl;

import com.example.backend.DTO.CreateUserProfileDto;
import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Entity.City;
import com.example.backend.Entity.Profile;
import com.example.backend.Entity.UserProfile;
import com.example.backend.Enums.Role;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Mapper.UserProfileMapper;
import com.example.backend.Repository.CityRepository;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Repository.UserProfileRepository;
import com.example.backend.Service.UserProfileService;
import io.jsonwebtoken.security.Password;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;
    @Qualifier("userProfileMapper")
    private final UserProfileMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final CityRepository cityRepository;

    @Override
    public List<UserProfileDto> getAll(){
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());

    }
    @Override
    public UserProfileDto getById(Long id){
        UserProfile userProfile = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserProfile nije pronađen: " + id));
        return mapper.toDto(userProfile);
    }

    @Override
    public UserProfileDto create(@Valid CreateUserProfileDto createDto){
        City city = cityRepository.findById(createDto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Grad nije pronađen: " + createDto.getCityId()));

        UserProfileDto dto = new UserProfileDto();

        dto.setName(createDto.getName());
        dto.setSurname(createDto.getSurname());
        dto.setEmail(createDto.getEmail());
        dto.setStatus(createDto.getStatus());
        dto.setPassword(createDto.getPassword());
        dto.setPhone(createDto.getPhone());
        dto.setAdverts(createDto.getAdverts());
        dto.setCreatedAt(createDto.getCreatedAt());
        dto.setAvatarUrl(createDto.getAvatarUrl());

        UserProfile userProfile = mapper.toEntity(dto);
        userProfile.setCity(city);
        userProfile.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userProfile.setRole(Role.USER);

        UserProfile saved = repository.save(userProfile);
        return mapper.toDto(saved);
    }

    @Override
    public UserProfileDto create(@Valid UserProfileDto dto){
        UserProfile userProfile = mapper.toEntity(dto);
        userProfile.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userProfile.setRole(Role.USER);

        UserProfile saved = repository.save(userProfile);
        return mapper.toDto(saved);
    }

    @Override
    // @PreAuthorize("hasRole('ADMIN') or @userProfileServiceImpl.isOwner(#dto.id, authentication.name)") // Disabled for testing
    public UserProfileDto update(@Valid @RequestBody UserProfileDto dto){
        UserProfile existing = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("UserProfile nije pronađen: " + dto.getId()));
        existing.setUserName(dto.getUserName());
        existing.setEmail(dto.getEmail());
        existing.setStatus(dto.getStatus());
        existing.setPhone(dto.getPhone());
        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }
        UserProfile saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    public boolean isOwner(Long profileId, String email) {
        return profileRepository.findById(profileId)
                .map(Profile::getEmail)
                .map(profileEmail -> Objects.equals(profileEmail, email))
                .orElse(false);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @userProfileServiceImpl.isOwner(#id, authentication.name)")
    public void delete(Long id){
        repository.deleteById(id);
    }
}