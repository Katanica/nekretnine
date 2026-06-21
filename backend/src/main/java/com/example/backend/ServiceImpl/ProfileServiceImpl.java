package com.example.backend.ServiceImpl;

import com.example.backend.DTO.ProfileDto;
import com.example.backend.Mapper.ProfileMapper;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileMapper.toDtoList(profileRepository.findAll());
    }

    @Override
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found: " + id);
        }
        profileRepository.deleteById(id);
    }
}