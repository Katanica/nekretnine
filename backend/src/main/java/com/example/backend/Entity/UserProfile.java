package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@Table(name = "USER_PROFILE")
@DiscriminatorValue("USER")
@NoArgsConstructor
public class UserProfile extends Profile {


    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "avatar")
    private String avatar;
}