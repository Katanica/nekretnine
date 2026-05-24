package com.example.backend.ServiceImpl;

import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Entity.UserProfile;
import com.example.backend.Enums.Role;
import com.example.backend.Mapper.UserProfileMapper;
import com.example.backend.Repository.UserProfileRepository;
import com.example.backend.Service.UserProfileService;
import io.jsonwebtoken.security.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;
    private final UserProfileMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserProfileDto> getAll(){
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());

    }
    @Override
    public UserProfileDto getById(Long id){
        UserProfile userProfile = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDto(userProfile);
    }

    @Override
    public UserProfileDto create(UserProfileDto dto){
        UserProfile userProfile = mapper.toEntity(dto);
        userProfile.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        userProfile.setRole(Role.USER);  // <-- DODAJ
        UserProfile saved = repository.save(userProfile);
        return mapper.toDto(saved);
    }

    @Override
    public UserProfileDto update(UserProfileDto dto){
        UserProfile existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        existing.setUserName(dto.getUserName());
        existing.setEmail(dto.getEmail());
        existing.setStatus(dto.getStatus());
        existing.setPhone(dto.getPhone());
        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setAvatar(dto.getAvatar());
        UserProfile saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }
}
