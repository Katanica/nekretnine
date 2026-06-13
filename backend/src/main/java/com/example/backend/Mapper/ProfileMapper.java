package com.example.backend.Mapper;

import com.example.backend.DTO.CreateProfileDto;
import com.example.backend.DTO.ProfileDto;
import com.example.backend.Entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses={CityMapper.class})
public interface ProfileMapper {

    ProfileDto toDto(Profile profile);

    @Mapping(target = "city", ignore = true)
    Profile toEntity(ProfileDto profileDto);

    List<ProfileDto> toDtoList(List<Profile> profiles);
}


