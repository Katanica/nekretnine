package com.example.backend.Controller;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.City;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Service.AdvertService;
import com.example.backend.Specification.AdvertFilterRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advert")
@AllArgsConstructor
public class AdvertController {

    private final AdvertService service;

    @GetMapping
    public ResponseEntity<Page<Advert>>  getAll(@RequestParam(defaultValue="0") int page,
                                                @RequestParam(defaultValue="10") int size){
        return ResponseEntity.ok(service.getAll(page, size));
    }

    // SPECIFICATION
    @GetMapping("/find")
    public List<Advert> getAdverts(@ModelAttribute AdvertFilterRequest filter){
        return service.searchAdverts(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<AdvertDto> create(@RequestBody AdvertDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<AdvertDto> update(@RequestBody AdvertDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
