package com.example.backend.DTO;

import com.example.backend.Entity.Advert;
import com.example.backend.Entity.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {
        private Long id;

        @NotBlank(message="Morate unijeti username")
        @Max(value=30, message="Username ne smije biti duži od 30 karaktera")
        private String userName;

        @NotBlank(message="Morate unijeti email")
        private String email;

        private Integer status;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
       // private City city;

        @NotBlank(message="Morate unijeti broj telefona")
        private String phone;

        private List<Advert> adverts;
}
