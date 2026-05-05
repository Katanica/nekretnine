package com.example.backend.Repository;

import com.example.backend.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findByAdvertId(Long advertId);
    List<Picture> findByAdvertIdAndIsPrimaryTrue(Long advertId);
}