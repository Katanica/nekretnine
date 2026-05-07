package com.example.backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgencyProfileDto extends ProfileDto{

    private Long id;

    private String userName;

    private String agencyName;

    private String email;

    private Integer status;

    private Integer phone;



}
