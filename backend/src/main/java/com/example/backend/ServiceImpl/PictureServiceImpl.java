package com.example.backend.ServiceImpl;

import com.example.backend.Entity.Advert;
import com.example.backend.Entity.Picture;
import com.example.backend.Repository.PictureRepository;
import com.example.backend.Service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository repository;

    @Override
    public List<Picture> getAll(Long advertId) {
        return repository.findByAdvertId(advertId);
    }

    @Override
    public Picture getById(Long pictureId) {
        return repository.findById(pictureId).orElseThrow();
    }

    @Override
    public Picture createPicture(Picture picture) {
        return repository.save(picture);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @pictureServiceImpl.isOwner(#pictureId, authentication.name)")
    public void deletePicture(Long pictureId) {
        repository.deleteById(pictureId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @pictureServiceImpl.isOwner(#pictureId, authentication.name)")
    public Picture setPrimary(Long pictureId) {
        // 1. nadji sliku koju zelimo postaviti kao primary
        Picture newPrimary = repository.findById(pictureId).orElseThrow();

        // 2. nadji trenutnu primary za isti oglas i skini joj flag
        List<Picture> currentPrimaries = repository
                .findByAdvertIdAndIsPrimaryTrue(newPrimary.getAdvert().getId());
        for (Picture p : currentPrimaries) {
            p.setIsPrimary(false);
            repository.save(p);
        }

        // 3. postavi novu kao primary
        newPrimary.setIsPrimary(true);
        return repository.save(newPrimary);
    }

    public boolean isOwner(Long pictureId, String email) {
        if (email == null) return false; // u slucaju da email nije poslan

        String ownerEmail = repository.findEmailByPictureId(pictureId).orElseThrow(() -> new RuntimeException("Picture is not found"));
        return ownerEmail.equals(email);
    }
}