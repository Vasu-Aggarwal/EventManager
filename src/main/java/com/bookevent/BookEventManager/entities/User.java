package com.bookevent.BookEventManager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    private String user_id;
    private String password;

    @Column(nullable = false)
    private String name;
    private String email;
    private String username;

    @ManyToMany
    @JoinTable()

    @ManyToMany

}
