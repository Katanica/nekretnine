package com.example.backend.Entity;
import com.example.backend.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
@Table(name = "PROFILE")
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "ROLE_id", discriminatorType = DiscriminatorType.STRING)
public class Profile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

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

    // PRIVREMENO STRING KASNIJE CEMO STAVIITI TIP LOCATION
    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @Column(name = "phone")
    private Integer phone;


    public Profile(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt, City city, Integer phone) {
    }
}