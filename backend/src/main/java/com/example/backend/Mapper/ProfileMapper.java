package com.example.backend.Mapper;

import com.example.backend.DTO.ProfileDto;
import com.example.backend.Entity.Profile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses={ProfileMapper.class})
public interface ProfileMapper {

    ProfileDto toDto(Profile profile);

    Profile toEntity(ProfileDto profileDto);

    List<ProfileDto> toDtoList(List<Profile> profiles);
}
