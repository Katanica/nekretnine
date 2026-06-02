package com.example.backend.Mapper;

import com.example.backend.DTO.BookmarkDto;
import com.example.backend.Entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AdvertMapper.class, ProfileMapper.class})
public interface BookmarkMapper {
    BookmarkDto toDto(Bookmark bookmark);

    Bookmark toEntity(BookmarkDto bookmarkDto);

    List<Bookmark> toDtoList(List<Bookmark> bookmark);

    void updateEntityFromDto(BookmarkDto dto, @MappingTarget Bookmark bookmark);
}
