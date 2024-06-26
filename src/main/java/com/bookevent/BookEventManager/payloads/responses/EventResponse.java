package com.bookevent.BookEventManager.payloads.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class EventResponse {

    private Integer event_id;

    @JsonFormat(pattern = "hh:mm:ss")
    private Time start_time;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date end_date;

    private int event_type; //private = 1, public 0
    private UserResponse created_by;

}
