package com.example.backend.Controller;

import com.example.backend.Entity.Picture;
import com.example.backend.Service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/picture")
@AllArgsConstructor
public class PictureController {

    private PictureService pictureService;

    @GetMapping("/advert/{advertId}")
    public List<Picture> getPicturesByAdvert(@PathVariable Long advertId) {
        return pictureService.getPicturesByAdvert(advertId);
    }

    @GetMapping("/{pictureId}")
    public Picture getPictureById(@PathVariable Long pictureId) {
        return pictureService.getPictureById(pictureId);
    }

    @PostMapping
    public Picture addPicture(@RequestBody Picture picture) {
        return pictureService.addPicture(picture);
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