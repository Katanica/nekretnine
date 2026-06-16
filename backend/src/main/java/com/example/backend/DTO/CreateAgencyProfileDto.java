package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message="Morate unijeti oib")
    private Integer oib;
}
