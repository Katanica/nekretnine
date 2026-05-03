package com.example.backend.Service;
import org.springframework.stereotype.Service;

import com.example.backend.Repository.CantonRepository;

@Service
public class CantonService {
    private final CantonRepository cantonRepository;

    public CantonService(CantonRepository cantonRepository) {
        this.cantonRepository = cantonRepository;
    }
}
