package com.example.backend.Mapper;

import com.example.backend.DTO.UserProfileDto;

import com.example.backend.Entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

   @Mapping(source = "city.id", target = "cityId")
   @Mapping(source = "city.name", target = "cityName")
   UserProfileDto toDto(UserProfile userProfile);

   @Mapping(target = "city", ignore = true)
   UserProfile toEntity(UserProfileDto userProfileDto);

   List<UserProfileDto> toDtoList(List<UserProfile> userProfiles);
}