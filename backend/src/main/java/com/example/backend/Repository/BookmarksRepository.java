package com.example.backend.Repository;

import com.example.backend.Entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarksRepository extends JpaRepository<Bookmark, Long> {
}
