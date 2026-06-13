package com.example.backend.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CreateUserProfileDto extends CreateProfileDto {
    @NotBlank(message="Morate unijeti ime")
    private String name;

    @NotBlank(message="Morate unijeti prezime")
    private String surname;
}
