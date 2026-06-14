package com.example.backend.Controller;

import com.example.backend.DTO.CantonDto;
import com.example.backend.Service.CantonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canton")
@AllArgsConstructor
public class CantonController {

    private final CantonService service;

    @GetMapping
    public ResponseEntity<List<CantonDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/register")
    public ResponseEntity<List<CantonDto>> getAllMin() {
        return ResponseEntity.ok(service.getAllWithCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CantonDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CantonDto> create(@Valid @RequestBody CantonDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<CantonDto> update(@Valid @RequestBody CantonDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}