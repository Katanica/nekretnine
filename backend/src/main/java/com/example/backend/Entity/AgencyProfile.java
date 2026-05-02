package com.example.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.Table;

@Entity
@AllArgsConstructor
@Data
@Table(name = "AGENCY_PROFILE")
@DiscriminatorValue("AGENCY")
public class AgencyProfile extends Profile{

    @Column(name = "agencyName")
    private String agencyName;

    @Column(name = "oib", unique = true)
    private String oib;


}
