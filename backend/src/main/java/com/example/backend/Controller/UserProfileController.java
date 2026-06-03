package com.example.backend.Controller;

import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Service.UserProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userProfile")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService service;

    @GetMapping
    public ResponseEntity<List<UserProfileDto>>  getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> create(@Valid @RequestBody UserProfileDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<UserProfileDto> update(@Valid @RequestBody UserProfileDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}