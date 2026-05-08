package com.example.backend.Service;

import com.example.backend.Entity.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> getPicturesByAdvert(Long advertId);
    Picture getPictureById(Long pictureId);
    Picture addPicture(Picture picture);
    void deletePicture(Long pictureId);
    Picture setPrimary(Long pictureId);
}