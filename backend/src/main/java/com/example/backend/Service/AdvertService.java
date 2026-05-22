package com.example.backend.Service;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Specification.AdvertFilterRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdvertService {

    List<Advert> searchAdverts(AdvertFilterRequest filter);

    Page<Advert> getAll(int page, int size);

    AdvertDto getById(Long id);

    AdvertDto create(AdvertDto dto);

    AdvertDto update(AdvertDto dto);

    void delete(Long id);
}
