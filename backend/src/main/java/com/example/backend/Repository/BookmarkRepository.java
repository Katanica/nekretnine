package com.example.backend.Repository;

import com.example.backend.Entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByProfileId(Long profileId);
    boolean existsByAdvertIdAndProfileId(Long advertId, Long profileId);
    Optional<Bookmark> findByAdvertIdAndProfileId(Long advertId, Long profileId);
}