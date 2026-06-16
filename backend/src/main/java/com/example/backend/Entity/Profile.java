package com.example.backend.Entity;
import com.example.backend.Enums.ProfileType;
import com.example.backend.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@Data
@Entity
@Table(name = "PROFILE")
@NoArgsConstructor
@Getter
@Setter
public class Profile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userName", unique = true)
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="profile_type")
    private ProfileType profileType;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @OneToMany(mappedBy="profile", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Advert> adverts = new ArrayList<>();

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar_url")
    private String avatarUrl;
}