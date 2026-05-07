package com.example.backend.Mapper;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.Entity.AgencyProfile;
import lombok.NoArgsConstructor;


public class AgencyProfileMapper {

    public AgencyProfileDto toDto(AgencyProfile agencyProfile){
        AgencyProfileDto dto = new AgencyProfileDto();

        dto.setId(agencyProfile.getId());
        dto.setUserName(agencyProfile.getUserName());
        dto.setAgencyName(agencyProfile.getAgencyName());
        dto.setEmail(agencyProfile.getEmail());
        dto.setStatus(agencyProfile.getStatus());
        dto.setPhone(agencyProfile.getPhone());

        return dto;

    }

    public AgencyProfile toEntity(AgencyProfileDto dto){

        AgencyProfile agencyProfile = new AgencyProfile();

        agencyProfile.setId(dto.getId());
        agencyProfile.setUserName(dto.getUserName());
        agencyProfile.setAgencyName(dto.getAgencyName());
        agencyProfile.setEmail(dto.getEmail());
        agencyProfile.setStatus(dto.getStatus());
        agencyProfile.setPhone(dto.getPhone());

        return agencyProfile;
    }
}
