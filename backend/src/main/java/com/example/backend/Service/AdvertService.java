package com.example.backend.Service;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Repository.AdvertRepository;
import org.springframework.stereotype.Service;

public interface AdvertService {
    AdvertDto addAdvert(AdvertDto advertDto);
}
