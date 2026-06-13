package com.example.backend.Repository;

import com.example.backend.Entity.Canton;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CantonRepository extends JpaRepository<Canton, Long> {
    @Query("SELECT DISTINCT c FROM Canton c JOIN FETCH c.cities")
    List<Canton> findAllWithCities();
}