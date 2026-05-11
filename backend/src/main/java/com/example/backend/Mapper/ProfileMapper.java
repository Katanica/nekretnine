package com.example.backend.Mapper;

import com.example.backend.DTO.ProfileDto;
import com.example.backend.Entity.Profile;

public class ProfileMapper {
    public static ProfileDto toDto(Profile profile){
        return new ProfileDto(
                profile.getId(),
                profile.getUsername(),
                profile.getEmail(),
                profile.getCreatedAt(),
                profile.getUpdatedAt(),
                profile.getCity(),
                profile.getPhone()
        );
    }

    public static Profile toEntity(ProfileDto profileDto){
        return new Profile(
                profileDto.getId(),
                profileDto.getUsername(),
                profileDto.getEmail(),
                profileDto.getCreatedAt(),
                profileDto.getUpdatedAt(),
                profileDto.getCity(),
                profileDto.getPhone()
        );
    }
}
