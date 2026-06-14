package com.example.backend.Controller;

import com.example.backend.DTO.*;
import com.example.backend.Service.AgencyProfileService;
import com.example.backend.ServiceImpl.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AgencyProfileService agencyProfileService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserProfileDto> registerUser(
            @Valid @RequestBody CreateUserProfileDto dto) throws IOException {
        return ResponseEntity.status(201).body(authService.registerUser(dto));
    }

    @PostMapping("/register/agency")
    public ResponseEntity<AgencyProfileDto> registerAgency(@Valid @RequestBody CreateAgencyProfileDto dto){
        return ResponseEntity.status(201).body(agencyProfileService.create(dto));
    }
}