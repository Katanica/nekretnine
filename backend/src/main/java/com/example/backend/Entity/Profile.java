package com.example.backend.Entity;
import com.example.backend.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@Data
@Entity
@Table(name = "PROFILE")
@NoArgsConstructor
@DiscriminatorColumn(name = "profile_type", discriminatorType = DiscriminatorType.STRING)
public class Profile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "status")
    private Integer status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @OneToMany(mappedBy="profile", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Advert> adverts = new ArrayList<>();

    @Column(name = "phone")
    private String phone;

}