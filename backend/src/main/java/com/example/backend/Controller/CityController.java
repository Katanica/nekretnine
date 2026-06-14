package com.example.backend.Controller;

import com.example.backend.DTO.CityDto;
import com.example.backend.Service.CityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
public class CityController {

    private final CityService service;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/byCanton/{cantonId}")
    public ResponseEntity<List<CityDto>> getByCanton(@PathVariable Long cantonId) {
        return ResponseEntity.ok(service.getByCanton(cantonId));
    }

    @PostMapping
    public ResponseEntity<CityDto> create(@Valid @RequestBody CityDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<CityDto> update(@Valid @RequestBody CityDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}