package com.example.backend.Mapper;

import com.example.backend.DTO.CantonDto;
import com.example.backend.Entity.Canton;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface CantonMapper {
    CantonDto toDto(Canton canton);

    Canton toEntity(CantonDto cantonDto);

    List<Canton> toDtoList(List<Canton> canton);
}
