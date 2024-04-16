package com.bookevent.BookEventManager.entities;


import jakarta.persistence.*;

import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    private Date start_time;
    private Duration duration;
    private int event_type;
    private String created_by;

}
