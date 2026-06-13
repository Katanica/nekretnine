package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"canton", "adverts", "profiles"})   // ← dodano
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="canton_id")
    @JsonIgnore
    private Canton canton;

    @OneToMany(mappedBy="city", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Advert> adverts = new ArrayList<>();

    @OneToMany(mappedBy="city", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Profile> profiles = new ArrayList<>();
}
