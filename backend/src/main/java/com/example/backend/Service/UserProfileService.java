package com.example.backend.Service;

import com.example.backend.DTO.UserProfileDto;

import java.util.List;

public interface UserProfileService {

    List<UserProfileDto> getAll();

    UserProfileDto getById(Long id);

    UserProfileDto create(UserProfileDto dto);

    UserProfileDto update(UserProfileDto dto);

    void delete(Long id);

}
