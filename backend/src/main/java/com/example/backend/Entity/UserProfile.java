package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name = "USER_PROFILE")
@DiscriminatorValue("USER")
@NoArgsConstructor
public class UserProfile extends Profile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "avatar")
    private String avatar;
}