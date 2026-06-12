package com.example.backend.Entity;

import com.example.backend.Enums.AdvertType;
import com.example.backend.Enums.PropertyType;
import com.example.backend.Enums.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="ADVERT")
public class Advert{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="property_type")
    private PropertyType propertyType;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    @Column(name="posted_at")
    private LocalDateTime postedAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="advert_type")
    private AdvertType advertType;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

    @Column(name="size")
    private Float size;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @ElementCollection
    @CollectionTable(name = "advert_pictures", joinColumns = @JoinColumn(name = "advert_id"))
    @Column(name = "image_url")
    @OrderColumn(name = "id")
    private List<String> imageUrls = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        postedAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}