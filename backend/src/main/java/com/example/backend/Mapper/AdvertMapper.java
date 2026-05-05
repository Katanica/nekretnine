package com.example.backend.Mapper;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;

public class AdvertMapper {
    public static AdvertDto mapToAdvertDto(Advert advert){
        return new AdvertDto(
                advert.getId(),
                advert.getPropertyType(),
                advert.getTitle(),
                advert.getDescription(),
                advert.getPrice(),
                advert.getPostedAt(),
                advert.getUpdatedAt(),
                advert.getAdvertType(),
                advert.getCity(),
                advert.getSize()
        );
    }
    public static Advert mapToAdvert(AdvertDto advertDto){
        return new Advert(
                advertDto.getId(),
                advertDto.getPropertyType(),
                advertDto.getTitle(),
                advertDto.getDescription(),
                advertDto.getPrice(),
                advertDto.getPostedAt(),
                advertDto.getUpdatedAt(),
                advertDto.getAdvertType(),
                advertDto.getCity(),
                advertDto.getSize()
        );
    }
}
