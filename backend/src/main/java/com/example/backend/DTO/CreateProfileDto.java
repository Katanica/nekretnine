package com.example.backend.DTO;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Canton;
import com.example.backend.Entity.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CreateProfileDto {
    private Long id;

    @NotBlank(message="Morate unijeti username")
    @Size(max=30, message="Username ne smije biti duži od 30 karaktera")
    private String userName;

    @NotBlank(message="Morate unijeti email")
    private String email;

    private Integer status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Long cityId;

    @NotBlank(message="Morate unijeti broj telefona")
    private String phone;

    private List<AdvertDto> adverts;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    private String avatarUrl;
}
