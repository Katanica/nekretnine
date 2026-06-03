package com.example.backend.Mapper;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses={CityMapper.class, PictureMapper.class})
public interface AdvertMapper {

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "city.name", target = "cityName")
    AdvertDto toDto(Advert advert);

    @Mapping(target = "city", ignore = true)
    Advert toEntity(AdvertDto advertDto);

    List<AdvertDto> toDtoList(List<Advert> advert);

    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "city", ignore = true)
    void updateEntityFromDto(AdvertDto dto, @MappingTarget Advert advert);
}