package com.example.backend.Service;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Specification.AdvertFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AdvertService {

    List<AdvertDto> searchAdverts(AdvertFilterRequest filter);

    Page<AdvertDto> getAll(int page, int size);

    AdvertDto getById(Long id);

    AdvertDto create(AdvertDto dto);

    @PreAuthorize("hasRole('ADMIN') or @advertServiceImpl.isOwner(#dto.id, authentication.name)")
    AdvertDto update(AdvertDto dto);

    @PreAuthorize("hasRole('ADMIN') or @advertServiceImpl.isOwner(#id, authentication.name)")
    void delete(Long id);

}
