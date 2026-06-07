package com.example.backend.Mapper;

import com.example.backend.DTO.AvatarDto;
import com.example.backend.Entity.Avatar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvatarMapper {
    AvatarDto toDto(Avatar avatar);

    Avatar toEntity(AvatarDto avatarDto);

    List<AvatarDto> toDtoList(List<Avatar> avatars);
}

