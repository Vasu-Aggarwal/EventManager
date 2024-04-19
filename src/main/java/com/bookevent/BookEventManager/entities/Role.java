package com.bookevent.BookEventManager.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    private Integer role_id;
    private int role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
