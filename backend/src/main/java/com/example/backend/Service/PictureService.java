package com.example.backend.Service;

import com.example.backend.Entity.Picture;
import com.example.backend.Repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public List<Picture> getPicturesByAdvert(Long advertId) {
        return pictureRepository.findByAdvertId(advertId);
    }

    public Picture getPictureById(Long pictureId) {
        return pictureRepository.findById(pictureId).orElseThrow();
    }

    public Picture addPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    public void deletePicture(Long pictureId) {
        pictureRepository.deleteById(pictureId);
    }

    public Picture setPrimary(Long pictureId) {
        // 1. nadji sliku koju zelimo postaviti kao primary
        Picture newPrimary = pictureRepository.findById(pictureId).orElseThrow();

        // 2. nadji trenutnu primary za isti oglas i skini joj flag
        List<Picture> currentPrimaries = pictureRepository
                .findByAdvertIdAndIsPrimaryTrue(newPrimary.getAdvert().getId());
        for (Picture p : currentPrimaries) {
            p.setIsPrimary(false);
            pictureRepository.save(p);
        }

        // 3. postavi novu kao primary
        newPrimary.setIsPrimary(true);
        return pictureRepository.save(newPrimary);
    }
}