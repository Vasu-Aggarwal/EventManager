package com.bookevent.BookEventManager.entities;


import jakarta.persistence.*;
import jdk.jfr.Description;

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

    private Date start_time;
    private Duration duration;
    private int event_type;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @Column(name = "comment_id")
    private List<Comment> comments = new ArrayList<>();

}
