package com.example.backend.Mapper;

import com.example.backend.DTO.UserProfileDto;

import com.example.backend.Entity.UserProfile;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

   UserProfileDto toDto(UserProfile userProfile);

   UserProfile toEntity(UserProfileDto userProfileDto);

   List<UserProfileDto> toDtoList(List<UserProfile> userProfiles);
}
