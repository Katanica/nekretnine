package com.example.backend.ServiceImpl;

import com.example.backend.Entity.Profile;
import com.example.backend.Repository.ProfileRepository;
import com.example.backend.Security.CustomUserDetails;
import com.example.backend.Security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService  implements UserDetailsService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Profile profile = profileRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(profile);
    }

    public String login(String email, String password){
        Profile profile = profileRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Incorrect Email or Password"));
        if(!passwordEncoder.matches(password, profile.getPasswordHash())){
            throw new RuntimeException("Incorrect email or password");
        }
        return jwtUtil.generateToken(new CustomUserDetails(profile));
    }
}
