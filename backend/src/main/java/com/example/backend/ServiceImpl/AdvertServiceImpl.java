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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository repository;
    @Qualifier("advertMapper")
    private final AdvertMapper mapper;

    public List<Advert> searchAdverts(AdvertFilterRequest filter){
        String title = filter.getTitle();
        PropertyType propertyType = filter.getPropertyType();
        Double maxPrice = filter.getMaxPrice();
        Double minPrice = filter.getMinPrice();
        Integer cityId = filter.getCityId();
        Integer cantonId = filter.getCantonId();
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

        return repository.findAll(spec);
    }

    @Override
    // OSPOSOBIT NA ADVERT DTO !!!
    public Page<Advert> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
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
    public AdvertDto update(AdvertDto dto){
        Advert existing = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        mapper.updateEntityFromDto(dto, existing);
        Advert saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }
}
