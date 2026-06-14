package com.example.backend.Repository;

import com.example.backend.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE LOWER(p.email) = LOWER(:email)")
    Optional<Profile> findByEmail(@Param("email") String email);
}