package com.example.backend.Controller;

import com.example.backend.DTO.CityDto;
import com.example.backend.Service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @GetMapping("/byCanton/{cantonId}")
    public ResponseEntity<List<CityDto>> getByCanton(@PathVariable Long cantonId) {
        return ResponseEntity.ok(cityService.getByCanton(cantonId));
    }
}