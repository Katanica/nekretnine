package com.example.backend.DTO;

import com.example.backend.Entity.Advert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {
        private Long id;

        private String userName;

        private String email;

        private Integer status;


        //CITY UBACIT KAD SE ZAVRSI CityService i ostalo

        private String phone;

        private List<Advert> adverts;
}
