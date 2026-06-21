package com.example.backend.DTO;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Canton;
import com.example.backend.Entity.City;
import com.example.backend.Enums.ProfileType;
import com.example.backend.Enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {
        private Long id;

        @NotBlank(message="Morate unijeti username")
        @Size(max=30, message="Username ne smije biti duži od 30 karaktera")
        private String userName;

        @NotBlank(message="Morate unijeti email")
        private String email;

        private Integer status;

        private Role role;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;

        private CityDto city;

        @Column(name="profile_type")
        private ProfileType profileType;

        @NotBlank(message="Morate unijeti broj telefona")
        private String phone;

        private List<AdvertDto> adverts;

        @Column(name = "createdAt")
        private LocalDateTime createdAt;

        private String avatarUrl;
}
