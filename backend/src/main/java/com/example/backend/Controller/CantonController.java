package com.example.backend.Controller;

import com.example.backend.DTO.CantonDto;
import com.example.backend.Service.CantonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canton")
@AllArgsConstructor
public class CantonController {

    private final CantonService cantonService;

    @GetMapping
    public ResponseEntity<List<CantonDto>> getAll() {
        return ResponseEntity.ok(cantonService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CantonDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cantonService.getById(id));
    }
}