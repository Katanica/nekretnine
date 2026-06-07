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
    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Avatar avatar;
}