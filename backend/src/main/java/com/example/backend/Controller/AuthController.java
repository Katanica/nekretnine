package com.example.backend.Controller;

import com.example.backend.DTO.AgencyProfileDto;
import com.example.backend.DTO.AuthResponse;
import com.example.backend.DTO.LoginRequest;
import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Service.AgencyProfileService;
import com.example.backend.Service.UserProfileService;
import com.example.backend.ServiceImpl.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserProfileService userProfileService;
    private final AgencyProfileService agencyProfileService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserProfileDto> registerUser(@Valid @RequestBody UserProfileDto dto){
        return ResponseEntity.status(201).body(userProfileService.create(dto));
    }

    @PostMapping("/register/agency")
    public ResponseEntity<AgencyProfileDto> registerAgency(@Valid @RequestBody AgencyProfileDto dto){
        return ResponseEntity.status(201).body(agencyProfileService.create(dto));
    }
}