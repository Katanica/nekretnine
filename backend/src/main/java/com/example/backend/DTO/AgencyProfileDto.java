package com.example.backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgencyProfileDto extends ProfileDto{
    private String agencyName;
    private String oib;
}
