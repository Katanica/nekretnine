package com.example.backend.ServiceImpl;

import com.example.backend.Entity.Picture;
import com.example.backend.Repository.PictureRepository;
import com.example.backend.Service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Override
    public List<Picture> getAll(Long advertId) {
        return pictureRepository.findByAdvertId(advertId);
    }

    @Override
    public Picture getById(Long pictureId) {
        return pictureRepository.findById(pictureId).orElseThrow();
    }

    @Override
    public Picture createPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void deletePicture(Long pictureId) {
        pictureRepository.deleteById(pictureId);
    }

    @Override
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