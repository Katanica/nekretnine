package com.example.backend.Mapper;

import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses={CityMapper.class})
public interface UserProfileMapper {

   UserProfileDto toDto(UserProfile userProfile);


   @Mapping(target = "adverts", ignore = true)
   UserProfile toEntity(UserProfileDto userProfileDto);

   List<UserProfileDto> toDtoList(List<UserProfile> userProfiles);
}