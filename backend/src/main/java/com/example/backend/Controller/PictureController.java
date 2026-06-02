package com.example.backend.Controller;

import com.example.backend.Entity.Advert;
import com.example.backend.Entity.Picture;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/picture")
@AllArgsConstructor
public class PictureController {

    private PictureService pictureService;
    private AdvertRepository advertRepository;

    @GetMapping("/advert/{advertId}")
    public List<Picture> getPicturesByAdvert(@PathVariable Long advertId) {
        return pictureService.getAll(advertId);
    }

    @GetMapping("/{pictureId}")
    public Picture getPictureById(@PathVariable Long pictureId) {
        return pictureService.getById(pictureId);
    }

    @PostMapping
    public Picture addPicture(@RequestBody Picture picture) {
        return pictureService.createPicture(picture);
    }

    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // consumes govori serveru da mora primiti neki file
    public ResponseEntity<List<Picture>> uploadImages(
            @PathVariable Long id,
            @RequestParam("files") List<MultipartFile> files) throws IOException {

        List<Picture> pictures = pictureService.uploadImages(id, files);
        return ResponseEntity.ok(pictures);
    }

    @DeleteMapping("/{pictureId}")
    public void deletePicture(@PathVariable Long pictureId) {
        pictureService.deletePicture(pictureId);
    }

    @PutMapping("/{pictureId}/primary")
    public Picture setPrimary(@PathVariable Long pictureId) {
        return pictureService.setPrimary(pictureId);
    }
}