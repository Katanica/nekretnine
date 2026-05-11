package com.example.backend.Mapper;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses={CityMapper.class})
public interface AdvertMapper {

    AdvertDto toDto(Advert advert);

    Advert toEntity(AdvertDto advertDto);

    List<AdvertDto> toDtoList(List<Advert> advert);

    void updateEntityFromDto(AdvertDto dto, @MappingTarget Advert advert);
}