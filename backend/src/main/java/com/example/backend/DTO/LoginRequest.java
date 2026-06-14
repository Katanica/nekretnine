package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Morate unijeti email")
    private String email;

    @NotBlank(message = "Morate unijeti lozinku")
    private String password;
}