package com.example.backend.Service;

import com.example.backend.DTO.PictureDto;
import com.example.backend.Entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    List<Picture> getAll(Long advertId);
    Picture getById(Long pictureId);
    List<Picture> savePictures(PictureDto dto);
    String detectContentType(String url);
    public List<Picture> uploadImages(Long advertId, List<MultipartFile> files) throws IOException;
    void deletePicture(Long pictureId);
    Picture setPrimary(Long pictureId);
}