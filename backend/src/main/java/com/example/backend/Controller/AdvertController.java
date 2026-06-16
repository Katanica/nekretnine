package com.example.backend.Controller;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.DTO.CreateAdvertDto;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Security.CustomUserDetails;
import com.example.backend.Service.AdvertService;
import com.example.backend.Specification.AdvertFilterRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advert")
@AllArgsConstructor
public class AdvertController {

    private final AdvertService service;
    private final AdvertRepository repository;

    @GetMapping("/countAdverts")
    public ResponseEntity<Long> countAdverts(){
        return ResponseEntity.ok(repository.count());
    }

    @GetMapping
    public ResponseEntity<Page<AdvertDto>>  getAll(@RequestParam(defaultValue="0") String page,
                                                   @RequestParam(defaultValue="10") String size){

        return ResponseEntity.ok(service.getAll(Integer.parseInt(page), Integer.parseInt(size)));
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
    public ResponseEntity<AdvertDto> create(@PathVariable Long id, @Valid @RequestBody CreateAdvertDto dto){
        return ResponseEntity.status(201).body(service.create(id, dto));
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