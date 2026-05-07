package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Mapper.AdvertMapper;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Service.AdvertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private AdvertRepository advertRepository;

    @Override
    public AdvertDto addAdvert(AdvertDto advertDto) {
        Advert advert = AdvertMapper.toEntity(advertDto);
        Advert savedAdvert = advertRepository.save(advert);
        return AdvertMapper.toDto(savedAdvert);
    }
}
