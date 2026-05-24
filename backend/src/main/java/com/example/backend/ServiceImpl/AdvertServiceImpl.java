package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.City;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Mapper.AdvertMapper;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Service.AdvertService;
import com.example.backend.Specification.AdvertFilterRequest;
import com.example.backend.Specification.AdvertSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository repository;
    @Qualifier("advertMapper")
    private final AdvertMapper mapper;

    public List<AdvertDto> searchAdverts(AdvertFilterRequest filter){
        String title = filter.getTitle();
        PropertyType propertyType = filter.getPropertyType();
        Double maxPrice = filter.getMaxPrice();
        Double minPrice = filter.getMinPrice();
        Long cityId = filter.getCityId();
        Long cantonId = filter.getCantonId();
        Float maxSize = filter.getMaxSize();
        Float minSize = filter.getMinSize();

        Specification<Advert> spec = Specification
                .where((root, query, cb) -> cb.conjunction());

        if (title != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        if(propertyType!=null){
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("propertyType")), String.valueOf(propertyType)));
        }
        if(maxPrice!=null){
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if(minPrice!=null){
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if(maxSize!=null){
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("size"), maxSize));
        }
        if(minSize!=null){
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("size"), minSize));
        }
        if(cityId!=null){
            spec = spec.and((root, query, cb) -> cb.equal(root.get("city").get("id"), cityId));
        }
        if(cantonId!=null){
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
        Advert advert = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDto(advert);
    }

    @Override
    public AdvertDto create(AdvertDto dto){
        Advert advert = mapper.toEntity(dto);
        Advert saved = repository.save(advert);
        return mapper.toDto(saved);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @advertServiceImpl.isOwner(#dto.id, authentication.name)")
    public AdvertDto update(AdvertDto dto){
        Advert existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        mapper.updateEntityFromDto(dto, existing);
        Advert saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    public boolean isOwner(Long advertId, String email) {
        return repository.findById(advertId)
                .map(ad -> {
                    System.out.println(ad.getProfile());
                    return ad.getProfile().getEmail().equals(email);
                })
                .orElse(false);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @advertServiceImpl.isOwner(#id, authentication.name)")
    public void delete(Long id){
        repository.deleteById(id);
    }
}
