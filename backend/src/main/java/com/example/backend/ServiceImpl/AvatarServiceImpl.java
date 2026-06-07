package com.example.backend.ServiceImpl;

import com.example.backend.Entity.Avatar;
import com.example.backend.Entity.UserProfile;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.AvatarRepository;
import com.example.backend.Repository.UserProfileRepository;
import com.example.backend.Service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final UserProfileRepository userProfileRepository;

    @Value("${upload.dir:uploads/avatars}")
    private String uploadDir;

    @Override
    public Avatar uploadAvatar(Long userProfileId, MultipartFile file) throws IOException {
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile nije pronađen: " + userProfileId));

        // Create folder for avatars
        Path uploadPath = Paths.get(uploadDir, userProfileId.toString());
        Files.createDirectories(uploadPath);

        // Delete old avatar if exists
        avatarRepository.findByUserProfileId(userProfileId).ifPresent(oldAvatar -> {
            try {
                if (oldAvatar.getFilePath() != null) {
                    Files.deleteIfExists(Paths.get(oldAvatar.getFilePath()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            avatarRepository.delete(oldAvatar);
        });

        // Save new avatar file
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Create and save Avatar entity
        Avatar avatar = new Avatar();
        avatar.setUserProfile(userProfile);
        avatar.setFileName(fileName);
        avatar.setFilePath(filePath.toString());
        avatar.setContentType(file.getContentType());

        return avatarRepository.save(avatar);
    }

    @Override
    public Avatar getByUserProfileId(Long userProfileId) {
        return avatarRepository.findByUserProfileId(userProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Avatar nije pronađen za UserProfile: " + userProfileId));
    }

    @Override
    public void deleteAvatar(Long avatarId) {
        Avatar avatar = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new ResourceNotFoundException("Avatar nije pronađen: " + avatarId));

        // Delete file from disk
        try {
            if (avatar.getFilePath() != null) {
                Files.deleteIfExists(Paths.get(avatar.getFilePath()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        avatarRepository.delete(avatar);
    }
}

