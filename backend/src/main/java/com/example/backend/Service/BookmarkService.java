package com.example.backend.Service;

import com.example.backend.DTO.BookmarkDto;
import com.example.backend.Entity.Bookmark;

import java.util.List;

public interface BookmarkService {
    List<BookmarkDto> getAll(Long profileId);
    BookmarkDto getById(Long bookmarkId);
    BookmarkDto createBookmark(Long advertId, Long profileId);
    void removeBookmark(Long advertId, Long profileId);
    boolean isBookmarked(Long advertId, Long profileId);

    Bookmark createBookmark(Bookmark bookmark);
}