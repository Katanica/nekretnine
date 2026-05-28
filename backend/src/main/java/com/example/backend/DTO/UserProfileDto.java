package com.example.backend.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@NoArgsConstructor
@ToString(callSuper = true)
public class UserProfileDto extends ProfileDto {
        private String name;

        private String surname;

        private String avatar;


}
