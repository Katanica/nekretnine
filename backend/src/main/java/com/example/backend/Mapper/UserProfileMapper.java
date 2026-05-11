package com.example.backend.Mapper;

import com.example.backend.DTO.UserProfileDto;

import com.example.backend.Entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

   //CITY UBACIT KAD SE ZAVRSI CityService i ostalo
    public UserProfileDto toDto(UserProfile userProfile) {

        UserProfileDto dto = new UserProfileDto();

        dto.setId(userProfile.getId());
        dto.setUserName(userProfile.getUserName());
        dto.setName(userProfile.getName());
        dto.setSurname(userProfile.getSurname());
        dto.setEmail(userProfile.getEmail());
        dto.setPhone(userProfile.getPhone());
        dto.setStatus(userProfile.getStatus());

        dto.setAvatar(userProfile.getAvatar());

    return dto;
    }

    public UserProfile toEntity(UserProfileDto userProfileDto){
        UserProfile userProfile = new UserProfile();

        userProfile.setId(userProfileDto.getId());
        userProfile.setUserName(userProfileDto.getUserName());
        userProfile.setName(userProfileDto.getName());
        userProfile.setSurname(userProfileDto.getSurname());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setStatus(userProfileDto.getStatus());
        userProfile.setPhone(userProfile.getPhone());
        userProfile.setAvatar(userProfile.getAvatar());

        return userProfile;
    }
}
