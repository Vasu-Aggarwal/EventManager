package com.bookevent.BookEventManager.entities;


import jakarta.persistence.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    @Column(nullable = false)
    private Date start_time;

    @Column(nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private int event_type;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @Column(name = "comment_id")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Invitation> invitations = new ArrayList<>();

}
