package com.example.backend.Service;

import com.example.backend.Entity.Bookmark;

import java.util.List;

public interface BookmarkService {
    List<Bookmark> getAllBookmarksForUser(Long profileId);
    Bookmark getBookmarkById(Long bookmarkId);
    Bookmark createBookmark(Bookmark bookmark);
    void removeBookmark(Long advertId, Long profileId);
    boolean isBookmarked(Long advertId, Long profileId);
}