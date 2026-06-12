package com.example.backend.Mapper;

import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

   @Mapping(target = "cityId", source = "city.id")
   @Mapping(target = "cityName", source = "city.name")
   @Mapping(target = "id", source = "id")
   @Mapping(target = "userName", source = "userName")
   @Mapping(target = "email", source = "email")
   @Mapping(target = "phone", source = "phone")
   @Mapping(target = "status", source = "status")
   @Mapping(target = "name", source = "name")
   @Mapping(target = "surname", source = "surname")
   @Mapping(target="avatarUrl", source = "avatarUrl")
   UserProfileDto toDto(UserProfile userProfile);

   @Mapping(target = "city", ignore = true)
   @Mapping(target = "adverts", ignore = true)
   UserProfile toEntity(UserProfileDto userProfileDto);

   List<UserProfileDto> toDtoList(List<UserProfile> userProfiles);
}