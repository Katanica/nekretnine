package com.example.backend.Service;

import com.example.backend.Entity.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> getAll(Long advertId);
    Picture getById(Long pictureId);
    Picture createPicture(Picture picture);
    void deletePicture(Long pictureId);
    Picture setPrimary(Long pictureId);
}