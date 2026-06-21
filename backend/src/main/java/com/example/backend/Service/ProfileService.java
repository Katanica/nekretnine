package com.example.backend.Service;

import com.example.backend.DTO.ProfileDto;

import java.util.List;

public interface ProfileService {
    List<ProfileDto> getAllProfiles();
    void deleteProfile(Long id);
}