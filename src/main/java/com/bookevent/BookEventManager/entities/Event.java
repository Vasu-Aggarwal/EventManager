package com.bookevent.BookEventManager.entities;


import com.bookevent.BookEventManager.payloads.responses.InviteUserResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    @Column(nullable = false)
    private Time start_time;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start_date;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end_date;

    @Column(nullable = false)
    private int event_type;    //private = 1, public 0

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User created_by;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @Column(name = "comment_id")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Invitation> invitations = new ArrayList<>();

}
