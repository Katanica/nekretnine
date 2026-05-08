package com.example.backend.Controller;

import com.example.backend.Entity.Bookmark;
import com.example.backend.Service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
@AllArgsConstructor
public class BookmarkController {

    private BookmarkService bookmarkService;

    @GetMapping("/profile/{profileId}")
    public List<Bookmark> getAllBookmarksForUser(@PathVariable Long profileId) {
        return bookmarkService.getAllBookmarksForUser(profileId);
    }

    @GetMapping("/{bookmarkId}")
    public Bookmark getBookmarkById(@PathVariable Long bookmarkId) {
        return bookmarkService.getBookmarkById(bookmarkId);
    }

    @PostMapping
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkService.createBookmark(bookmark);
    }

    @DeleteMapping
    public void removeBookmark(@RequestParam Long advertId, @RequestParam Long profileId) {
        bookmarkService.removeBookmark(advertId, profileId);
    }

    @GetMapping("/check")
    public boolean isBookmarked(@RequestParam Long advertId, @RequestParam Long profileId) {
        return bookmarkService.isBookmarked(advertId, profileId);
    }
}