package com.example.backend.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor

public class UserProfileDto extends ProfileDto {
        private String name;

        private String surname;

        private String avatar;


}
