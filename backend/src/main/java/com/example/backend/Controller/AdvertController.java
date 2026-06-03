package com.example.backend.Controller;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.City;
import com.example.backend.Entity.Picture;
import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Security.CustomUserDetails;
import com.example.backend.Service.AdvertService;
import com.example.backend.Service.PictureService;
import com.example.backend.Specification.AdvertFilterRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/advert")
@AllArgsConstructor
public class AdvertController {

    private final AdvertService service;
    private final PictureService pictureService;

    @GetMapping
    public ResponseEntity<Page<AdvertDto>>  getAll(@RequestParam(defaultValue="0") int page,
                                                   @RequestParam(defaultValue="10") int size){
        return ResponseEntity.ok(service.getAll(page, size));
    }

    // SPECIFICATION
    @GetMapping("/find")
    public List<AdvertDto> getAdverts(@ModelAttribute AdvertFilterRequest filter){
        return service.searchAdverts(filter);
    }

    // Oglasi ulogovanog korisnika
    @GetMapping("/myAdverts")
    public ResponseEntity<List<AdvertDto>> getMyAdverts(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(service.getMyAdverts(userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<AdvertDto> create(@PathVariable Long id, @Valid @RequestBody AdvertDto dto){
        return ResponseEntity.status(201).body(service.create(id, dto));
    }
    @PostMapping(value = "/{id}/with-pictures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdvertDto> createWithPictures(
            @PathVariable Long id,
            @RequestParam("propertyType") String propertyType,
            @RequestParam("advertType") String advertType,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("size") Double size,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {

        AdvertDto dto = new AdvertDto();
        dto.setPropertyType(PropertyType.valueOf(propertyType));
        dto.setAdvertType(AdvertType.valueOf(advertType));
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setSize(size.floatValue());

        AdvertDto created = service.create(id, dto);

        if (files != null && !files.isEmpty()) {
            List<Picture> uploaded = pictureService.uploadImages(created.getId(), files);
            if (!uploaded.isEmpty()) pictureService.setPrimary(uploaded.get(0).getId());
        }

        return ResponseEntity.status(201).body(service.getById(created.getId()));
    }

    @PutMapping
    public ResponseEntity<AdvertDto> update(@Valid @RequestBody AdvertDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}