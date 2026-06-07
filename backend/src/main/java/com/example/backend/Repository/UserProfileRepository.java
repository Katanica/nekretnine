package com.example.backend.Repository;

import com.example.backend.Entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query("SELECT DISTINCT u FROM UserProfile u " +
           "LEFT JOIN FETCH u.avatar " +
           "WHERE u.id = :id")
    Optional<UserProfile> findByIdWithAvatar(@Param("id") Long id);
}