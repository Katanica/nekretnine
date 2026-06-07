package com.example.backend.Mapper;

import com.example.backend.DTO.ProfileDto;
import com.example.backend.Entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "cityName", source = "city.name")
    ProfileDto toDto(Profile profile);

    @Mapping(target = "city", ignore = true)
    Profile toEntity(ProfileDto profileDto);

    List<ProfileDto> toDtoList(List<Profile> profiles);
}


