package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {
        private Long id;

        private String userName;

        private String email;

        private Integer status;


        //CITY UBACIT KAD SE ZAVRSI CityService i ostalo

        private Integer phone;


}
