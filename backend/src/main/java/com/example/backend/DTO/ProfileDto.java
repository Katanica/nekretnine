package com.example.backend.DTO;

import com.example.backend.Entity.Advert;
import com.example.backend.Entity.City;
import com.fasterxml.jackson.annotation.JsonProperty;
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

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;




       // private City city;

        private String phone;

        private List<Advert> adverts;
}
