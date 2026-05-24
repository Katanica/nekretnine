package com.example.backend.Mapper;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Mapper.AdvertMapper;
import org.mapstruct.Mapper;

import com.example.backend.DTO.CityDto;
import com.example.backend.Entity.City;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AdvertMapper.class})
public interface CityMapper {
    CityDto toDto(City city);

    City toEntity(CityDto cityDto);

    List<CityDto> toDtoList(List<CityDto> cityDto);

    void updateEntityFromDto(CityDto dto, @MappingTarget City city);
}
