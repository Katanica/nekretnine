package com.example.backend.Service;

import com.example.backend.Entity.Bookmark;
import com.example.backend.Repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookmarkService {
     private final BookmarkRepository bookmarkRepository;
     public BookmarkService(BookmarkRepository bookmarkRepository) {
          this.bookmarkRepository = bookmarkRepository;
     }

     public List<Bookmark> getAllBookmarksForUser(Long profileId) {
          return bookmarkRepository.findByProfileId(profileId);
     }
     public Bookmark getBookmarkById(Long bookmarkId) {
          return bookmarkRepository.findById(bookmarkId).orElseThrow();
     }
     public Bookmark createBookmark(Bookmark bookmark) {
          return bookmarkRepository.save(bookmark);
     }
     public void removeBookmark(Long advertId, Long profileId) {
          Bookmark bookmark = bookmarkRepository.findByAdvertIdAndProfileId(advertId, profileId).orElseThrow();
          bookmarkRepository.delete(bookmark);
     }
     public boolean isBookmarked(Long advertId, Long profileId) {
          return bookmarkRepository.existsByAdvertIdAndProfileId(advertId, profileId);
     }
}
