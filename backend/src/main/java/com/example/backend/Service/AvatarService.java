package com.example.backend.Service;

import com.example.backend.Entity.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    Avatar uploadAvatar(Long userProfileId, MultipartFile file) throws IOException;
    Avatar getByUserProfileId(Long userProfileId);
    void deleteAvatar(Long avatarId);
}

