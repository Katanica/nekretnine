package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="canton_id")
    private Canton canton;

    @OneToMany(mappedBy="city", cascade=CascadeType.ALL)
    private List<Advert> adverts = new ArrayList<>();
}
