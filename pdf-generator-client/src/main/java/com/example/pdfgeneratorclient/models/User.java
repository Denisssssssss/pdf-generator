package com.example.pdfgeneratorclient.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor
public class User {

    public User(String username, String hashPassword) {
        this.username = username;
        this.hashPassword = hashPassword;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String hashPassword;
}
