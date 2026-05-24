package com.example.backend.Repository;

import com.example.backend.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findByAdvertId(Long advertId);
    List<Picture> findByAdvertIdAndIsPrimaryTrue(Long advertId);

    @Query("SELECT p.advert.profile.email FROM Picture p WHERE p.id = :pictureId")
    Optional<String> findEmailByPictureId(@Param("pictureId") Long pictureId);
}