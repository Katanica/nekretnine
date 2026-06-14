package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CreateAgencyProfileDto extends CreateProfileDto{
    @NotBlank(message="Morate unijeti ime agencije")
    private String agencyName;
    @NotBlank(message="Morate unijeti oib")
    private String oib;
}
