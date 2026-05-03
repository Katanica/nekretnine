package com.example.backend.Entity;
import com.example.backend.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@Data
@Entity
@Table(name = "PROFILE")
@NoArgsConstructor
@DiscriminatorColumn(name = "ROLE_id", discriminatorType = DiscriminatorType.STRING)
public abstract class Profile{

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
    @Column(name = "ROLE_id", insertable = false, updatable = false)
    private Role role;

    // PRIVREMENO STRING KASNIJE CEMO STAVIITI TIP LOCATION
    @Column(name = "location")
    private String location;

    @Column(name = "phone")
    private Integer phone;





}