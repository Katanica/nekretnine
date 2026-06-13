package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.DTO.CreateAdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.City;
import com.example.backend.Entity.Profile;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Mapper.AdvertMapper;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Repository.CityRepository;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Service.AdvertService;
import com.example.backend.Specification.AdvertFilterRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository repository;
    private final ProfileRepository profileRepository;
    private final CityRepository cityRepository;

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
    public List<AdvertDto> getMyAdverts(Long profileId){
        List<Advert> adverts = repository.findByProfileId(profileId);
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
        Advert advert = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oglas nije pronađen: " + id));
        return mapper.toDto(advert);
    }

    @Override
    public AdvertDto create(Long id, @Valid @RequestBody AdvertDto dto){
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Korisnik nije pronađen: " + id));
        Advert advert = mapper.toEntity(dto);
        advert.setProfile(profile);

        advert.setPostedAt(LocalDateTime.now());
        Advert saved = repository.save(advert);
        return mapper.toDto(saved);
    }

    @Override
    public AdvertDto create(Long id, @Valid @RequestBody CreateAdvertDto createDto){
        System.out.println("GABRIJEL: " + createDto.getCityId() + "!!!!!!!!!!!!!!" + createDto);

        Profile profile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Korisnik nije pronađen: " + id));
        City city = cityRepository.findById(createDto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Grad nije pronađen: " + createDto.getCityId()));

        System.out.println("GRAD!!!!!!!!!!!!!" + city);

        Advert advert = new Advert();

        advert.setPropertyType(createDto.getPropertyType());
        advert.setTitle(createDto.getTitle());
        advert.setDescription(createDto.getDescription());
        advert.setPrice(createDto.getPrice());
        advert.setAdvertType(createDto.getAdvertType());
        advert.setCity(city);
        advert.setProfile(profile);
        advert.setSize(createDto.getSize());
        advert.setStatus(createDto.getStatus());
        advert.setPostedAt(LocalDateTime.now());
        advert.setImageUrls(createDto.getImageUrls());

        Advert saved = repository.save(advert);
        return mapper.toDto(saved);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @advertServiceImpl.isOwner(#dto.id, authentication.name)")
    public AdvertDto update(@Valid @RequestBody AdvertDto dto){
        Advert existing = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Oglas nije pronađen: " + dto.getId()));
        mapper.updateEntityFromDto(dto, existing);
        /*if(dto.getCityId()!=null){
            City city = cityRepository.findById(dto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Grad nije pronađen: " + dto.getCityId()));
            existing.setCity(city);
        }*/
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