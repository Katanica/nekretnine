package com.example.backend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Data
@Entity
@Table(name = "USER_PROFILE")
@DiscriminatorValue("USER")
@NoArgsConstructor
public class UserProfile extends Profile {

    @Column(name = "firstName")
    private Integer name;

    @Column(name = "surname")
    private Integer surname;

    @Column(name = "avatar")
    private String avatar;
}
