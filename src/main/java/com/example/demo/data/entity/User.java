package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity @Table(name = "t_user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description", nullable = false)
    private String description;
}
