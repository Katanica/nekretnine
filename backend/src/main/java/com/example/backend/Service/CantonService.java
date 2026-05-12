package com.example.backend.Service;
import com.example.backend.DTO.CantonDto;
import com.example.backend.Entity.Canton;
import org.springframework.stereotype.Service;

import com.example.backend.Repository.CantonRepository;

import java.util.List;

@Service
public interface CantonService {
    List<CantonDto> findAll();
    Canton findById(Long profileId);

}
