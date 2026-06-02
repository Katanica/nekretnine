package com.example.backend.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@NoArgsConstructor
@ToString(callSuper = true)
public class UserProfileDto extends ProfileDto {
        @NotBlank(message="Morate unijeti ime")
        private String name;

        @NotBlank(message="Morate unijeti prezime")
        private String surname;

        private String avatar;


}
