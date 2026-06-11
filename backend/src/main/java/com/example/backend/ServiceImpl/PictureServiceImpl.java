package com.example.backend.ServiceImpl;

import com.example.backend.DTO.PictureDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.Picture;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Repository.PictureRepository;
import com.example.backend.Service.PictureService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository repository;
    private final AdvertRepository advertRepository;

    @Override
    public List<Picture> getAll(Long advertId) {
        return repository.findByAdvertId(advertId);
    }

    @Override
    public Picture getById(Long pictureId) {
        return repository.findById(pictureId).orElseThrow();
    }

    @Override
    public List<Picture> savePictures(PictureDto dto) {
        Advert advert = advertRepository.findById(dto.getAdvertId())
                .orElseThrow(() -> new RuntimeException("Oglas nije pronađen"));

        List<Picture> pictures = new ArrayList<>();
        boolean first = true;

        for (String url : dto.getUrls()) {
            Picture picture = new Picture();
            picture.setAdvert(advert);
            picture.setFilePath(url);
            picture.setFileName(url.substring(url.lastIndexOf('/') + 1));
            picture.setContentType(detectContentType(url));
            picture.setIsPrimary(first);
            first = false;
            pictures.add(picture);
        }

        return repository.saveAll(pictures);
    }

    @Override
    public String detectContentType(String url) {
        if (url.endsWith(".png")) return "image/png";
        if (url.endsWith(".webp")) return "image/webp";
        return "image/jpeg";
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


    // čita gdje će se slike spremati na disku - iz application properties
    @Value("${upload.dir:uploads/adverts}") // trazi upload.dir, a ako ga nema uzmi vrijednost uploads/adverts
    private String uploadDir;

    public List<Picture> uploadImages(Long advertId, List<MultipartFile> files) throws IOException {
        Advert advert = advertRepository.findById(advertId)
                .orElseThrow(() -> new ResourceNotFoundException("Oglas nije pronađen: id " + advertId) );

        // kreira folder za svaki advert u kojem se spremaju njegove slike
        Path uploadPath = Paths.get(uploadDir, advertId.toString());
        Files.createDirectories(uploadPath);

        List<Picture> savedImages = new ArrayList<>();

        // prođi kroz svaki file
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // generiranje jedinstvenog imena
            Path filePath = uploadPath.resolve(fileName); // resolve - dobiva konacčnu putanju foldera i file-a
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Picture picture = new Picture();
            picture.setAdvert(advert);
            picture.setFileName(fileName);
            picture.setFilePath(filePath.toString());
            picture.setContentType(file.getContentType());

            savedImages.add(repository.save(picture));
        }

        return savedImages;
    }
}