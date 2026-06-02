package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgencyProfileDto extends ProfileDto{
    @NotBlank(message="Morate unijeti ime agencije")
    private String agencyName;
    @NotBlank(message="Morate unijeti oib")
    private String oib;
}
