package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.Profile;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Mapper.AdvertMapper;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Security.CustomUserDetails;
import com.example.backend.Service.AdvertService;
import com.example.backend.Specification.AdvertFilterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository repository;

    @Qualifier("advertMapper")
    private final AdvertMapper mapper;

    @Override
    public List<AdvertDto> searchAdverts(AdvertFilterRequest filter){
        String title = filter.getTitle();
        PropertyType propertyType = filter.getPropertyType();
        Double maxPrice = filter.getMaxPrice();
        Double minPrice = filter.getMinPrice();
        Long cityId = filter.getCityId();
        Long cantonId = filter.getCantonId();
        Float maxSize = filter.getMaxSize();
        Float minSize = filter.getMinSize();

        // pocni sa praznom (always-true) specifikacijom pa dodaji uslove po potrebi
        Specification<Advert> spec = (root, query, cb) -> cb.conjunction();

        if (title != null && !title.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        if (propertyType != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("propertyType"), propertyType));
        }
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxSize != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("size"), maxSize));
        }
        if (minSize != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("size"), minSize));
        }
        if (cityId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("city").get("id"), cityId));
        }
        if (cantonId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("city").get("canton").get("id"), cantonId));
        }

        List<Advert> adverts = repository.findAll(spec);
        return mapper.toDtoList(adverts);
    }

    @Override
    public Page<AdvertDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Advert> adverts = repository.findAll(pageable);
        return adverts.map(mapper::toDto);
    }

    @Override
    public AdvertDto getById(Long id){
        Advert advert = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advert not found"));
        return mapper.toDto(advert);
    }


    @Override
    public AdvertDto create(AdvertDto dto){
        Advert advert = mapper.toEntity(dto);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (CustomUserDetails) auth.getPrincipal();
        advert.setProfile(userDetails.getProfile());

        Advert saved = repository.save(advert);
        return mapper.toDto(saved);
    }

    @Override
    public AdvertDto update(AdvertDto dto){
        Advert existing = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Advert not found"));
        mapper.updateEntityFromDto(dto, existing);
        Advert saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }

    // Helper za @PreAuthorize na interface metodama
    public boolean isOwner(Long advertId, String email) {
        return repository.findById(advertId)
                .map(Advert::getProfile)
                .map(Profile::getEmail)
                .map(e -> e.equalsIgnoreCase(email))
                .orElse(false);
    }
}