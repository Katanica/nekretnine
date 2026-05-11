package com.example.backend.Mapper;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AdvertMapper {
    public AdvertDto toDto(Advert advert){
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
    public Advert toEntity(AdvertDto advertDto){
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
