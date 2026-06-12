package com.example.backend.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class UserProfileDto extends ProfileDto {
        @NotBlank(message="Morate unijeti ime")
        private String name;

        @NotBlank(message="Morate unijeti prezime")
        private String surname;
}
