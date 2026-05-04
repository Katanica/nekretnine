package com.example.backend.Service;

import com.example.backend.Repository.AgencyProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class AgencyProfileService {
    private final AgencyProfileRepository agencyProfileRepository;

    public AgencyProfileService(AgencyProfileRepository agencyProfileRepository){
        this.agencyProfileRepository = agencyProfileRepository;
    }
}
