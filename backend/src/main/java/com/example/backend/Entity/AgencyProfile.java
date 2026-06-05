package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "AGENCY_PROFILE")
@DiscriminatorValue("AGENCY")
public class AgencyProfile extends Profile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "agencyName")
    private String agencyName;

    @Column(name = "oib", unique = true)
    private String oib;


}
