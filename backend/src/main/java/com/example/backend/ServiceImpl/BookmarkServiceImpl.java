package com.example.backend.ServiceImpl;

import com.example.backend.Entity.Bookmark;
import com.example.backend.Repository.BookmarkRepository;
import com.example.backend.Service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> getAllBookmarksForUser(Long profileId) {
        return bookmarkRepository.findByProfileId(profileId);
    }

    @Override
    public Bookmark getBookmarkById(Long bookmarkId) {
        return bookmarkRepository.findById(bookmarkId).orElseThrow();
    }

    @Override
    public Bookmark createBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Override
    public void removeBookmark(Long advertId, Long profileId) {
        Bookmark bookmark = bookmarkRepository.findByAdvertIdAndProfileId(advertId, profileId).orElseThrow();
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public boolean isBookmarked(Long advertId, Long profileId) {
        return bookmarkRepository.existsByAdvertIdAndProfileId(advertId, profileId);
    }
}