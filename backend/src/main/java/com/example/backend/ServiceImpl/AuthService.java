package com.example.backend.ServiceImpl;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.DTO.CreateAgencyProfileDto;
import com.example.backend.DTO.CreateUserProfileDto;
import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Enums.ProfileType;
import com.example.backend.Entity.Profile;
import com.example.backend.Exception.InvalidCredentialsException;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Security.CustomUserDetails;
import com.example.backend.Security.JwtUtil;
import com.example.backend.Service.AgencyProfileService;
import com.example.backend.Service.AvatarService;
import com.example.backend.Service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AuthService  implements UserDetailsService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AgencyProfileService agencyProfileService;
    private final UserProfileService userProfileService;

    private final AvatarService avatarService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Profile profile = profileRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(profile);
    }

    public String login(String email, String password){
        Profile profile = profileRepository.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException("Pogrešan email ili lozinka"));
        if(!passwordEncoder.matches(password, profile.getPasswordHash())){
            throw new InvalidCredentialsException("Pogrešan email ili lozinka");
        }
        return jwtUtil.generateToken(new CustomUserDetails(profile));
    }

    public UserProfileDto registerUser(CreateUserProfileDto dto) throws IOException {
        UserProfileDto created = userProfileService.create(dto);

        return userProfileService.getById(created.getId());
    }
    public AgencyProfileDto registerAgency(CreateAgencyProfileDto dto) throws IOException {
        AgencyProfileDto created = agencyProfileService.create(dto);

        return agencyProfileService.getById(created.getId());
    }

}