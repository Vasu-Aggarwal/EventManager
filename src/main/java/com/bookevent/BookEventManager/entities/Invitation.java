package com.bookevent.BookEventManager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @JoinColumn(name = "event_id")
    private Event event;

}
