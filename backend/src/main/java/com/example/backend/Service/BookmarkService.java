package com.example.backend.Service;

import com.example.backend.Entity.Bookmark;

import java.util.List;

public interface BookmarkService {
    List<Bookmark> getAll(Long profileId);
    Bookmark getById(Long bookmarkId);
    Bookmark createBookmark(Bookmark bookmark);
    void removeBookmark(Long advertId, Long profileId);
    boolean isBookmarked(Long advertId, Long profileId);
}