package com.example.backend.Mapper;

import com.example.backend.DTO.PictureDto;
import com.example.backend.Entity.Picture;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AdvertMapper.class})
public interface PictureMapper {
    PictureDto toDto(Picture picture);

    Picture toEntity(PictureDto pictureDto);

    List<Picture> toDtoList(List<Picture> picture);
}
