package com.example.backend.Controller;

import com.example.backend.DTO.BookmarkDto;
import com.example.backend.Service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
@AllArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<BookmarkDto>> getAllBookmarksForUser(@PathVariable Long profileId) {
        return ResponseEntity.ok(bookmarkService.getAll(profileId));
    }

    @GetMapping("/{bookmarkId}")
    public ResponseEntity<BookmarkDto> getBookmarkById(@PathVariable Long bookmarkId) {
        return ResponseEntity.ok(bookmarkService.getById(bookmarkId));
    }

    @PostMapping
    public ResponseEntity<BookmarkDto> createBookmark(@RequestParam Long advertId, @RequestParam Long profileId) {
        return ResponseEntity.status(201).body(bookmarkService.createBookmark(advertId, profileId));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeBookmark(@RequestParam Long advertId, @RequestParam Long profileId) {
        bookmarkService.removeBookmark(advertId, profileId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isBookmarked(@RequestParam Long advertId, @RequestParam Long profileId) {
        return ResponseEntity.ok(bookmarkService.isBookmarked(advertId, profileId));
    }
}