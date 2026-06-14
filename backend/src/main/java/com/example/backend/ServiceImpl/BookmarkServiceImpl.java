package com.example.backend.ServiceImpl;

import com.example.backend.DTO.BookmarkDto;
import com.example.backend.Entity.Advert;
import com.example.backend.Entity.Bookmark;
import com.example.backend.Entity.Profile;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Mapper.BookmarkMapper;
import com.example.backend.Repository.AdvertRepository;
import com.example.backend.Repository.BookmarkRepository;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final AdvertRepository advertRepository;
    private final ProfileRepository profileRepository;
    private final BookmarkMapper mapper;

    @Override
    public List<BookmarkDto> getAll(Long profileId) {
        return mapper.toDtoList(bookmarkRepository.findByProfileId(profileId));
    }

    @Override
    public BookmarkDto getById(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark nije pronađen: " + bookmarkId));
        return mapper.toDto(bookmark);
    }

    @Override
    public BookmarkDto createBookmark(Long advertId, Long profileId) {
        // ako bookmark već postoji, vrati postojeći (idempotentno - nema duplikata)
        Bookmark existing = bookmarkRepository.findByAdvertIdAndProfileId(advertId, profileId).orElse(null);
        if (existing != null) {
            return mapper.toDto(existing);
        }

        Advert advert = advertRepository.findById(advertId)
                .orElseThrow(() -> new ResourceNotFoundException("Oglas nije pronađen: " + advertId));
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik nije pronađen: " + profileId));

        Bookmark bookmark = new Bookmark();
        bookmark.setAdvert(advert);
        bookmark.setProfile(profile);

        return mapper.toDto(bookmarkRepository.save(bookmark));
    }

    @Override
    public void removeBookmark(Long advertId, Long profileId) {
        Bookmark bookmark = bookmarkRepository.findByAdvertIdAndProfileId(advertId, profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark nije pronađen"));
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public boolean isBookmarked(Long advertId, Long profileId) {
        return bookmarkRepository.existsByAdvertIdAndProfileId(advertId, profileId);
    }

    @Override
    public Bookmark createBookmark(Bookmark bookmark) {
        return null;
    }
}