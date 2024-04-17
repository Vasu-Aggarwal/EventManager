package com.bookevent.BookEventManager.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "invitation")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invite_id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User invitedUser;

}
