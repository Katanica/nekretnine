package com.example.backend.Controller;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.DTO.CreateAgencyProfileDto;
import com.example.backend.Service.AgencyProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencyProfile")
@AllArgsConstructor
public class AgencyProfileController {

    private final AgencyProfileService service;

    @GetMapping
    public ResponseEntity<List<AgencyProfileDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgencyProfileDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<AgencyProfileDto> create(@Valid @RequestBody AgencyProfileDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<AgencyProfileDto> update(@Valid @RequestBody CreateAgencyProfileDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}