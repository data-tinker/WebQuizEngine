package com.ttymonkey.webquizengine.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique=true)
    private String username;

    @Column(name = "password")
    private String password;

    public UserEntity(
        String username,
        String password
    ) {
        this.username = username;
        this.password = password;
    }
}
