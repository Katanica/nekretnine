package com.example.backend.Repository;

import com.example.backend.Entity.Canton;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface CantonRepository extends JpaRepository<Canton, Long> {

}