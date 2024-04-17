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

    @ManyToOne
    private Event event;

    @ManyToOne
    private User invitedUser;

}
