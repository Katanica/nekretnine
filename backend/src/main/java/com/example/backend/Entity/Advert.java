package com.example.backend.Entity;

import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="ADVERT")
public class Advert{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="property-type")
    private PropertyType propertyType;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="posted-at")
    private LocalDateTime postedAt;

    @Column(name="updated-at")
    private LocalDateTime updatedAt;

    @Column(name="advert-type")
    private AdvertType advertType;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @Column(name="size")
    private Float size;
}