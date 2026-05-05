package com.example.backend.Controller;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Service.AdvertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advert")
@AllArgsConstructor
public class AdvertController {
    private AdvertService advertService;

    @PostMapping
    public ResponseEntity<AdvertDto> addAdvert(@RequestBody AdvertDto advertDto){
        AdvertDto savedAdvert = advertService.addAdvert(advertDto);
        return new ResponseEntity<>(savedAdvert, HttpStatus.CREATED);
    }
}
