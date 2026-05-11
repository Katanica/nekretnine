package com.example.backend.Mapper;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.Entity.AgencyProfile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyProfileMapper {

    AgencyProfileDto toDto(AgencyProfile agencyProfile);

    AgencyProfile toEntity(AgencyProfileDto agencyProfileDto);

    List<AgencyProfileDto> toDtoList(List<AgencyProfile> agencyProfiles);
}
