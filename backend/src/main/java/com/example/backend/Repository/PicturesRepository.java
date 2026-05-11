package com.example.backend.Repository;

import com.example.backend.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicturesRepository extends JpaRepository<Picture, Long> {
}
