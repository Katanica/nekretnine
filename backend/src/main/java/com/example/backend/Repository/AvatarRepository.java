package com.example.backend.Repository;

import com.example.backend.Entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByUserProfileId(Long userProfileId);
}

