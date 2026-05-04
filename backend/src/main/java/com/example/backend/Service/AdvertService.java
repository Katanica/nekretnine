package com.example.backend.Service;

import com.example.backend.Repository.AdvertRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository){
        this.advertRepository = advertRepository;
    }
}
