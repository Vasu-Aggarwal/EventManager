package com.bookevent.BookEventManager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "invitation")
@Data
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invite_id;

    private int is_invited;

    @ManyToOne
    @JoinColumn(name = "invited_user_id")
    private User invited_user;

    @ManyToOne
    private Event event;

}
