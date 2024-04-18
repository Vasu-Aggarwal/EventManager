package com.bookevent.BookEventManager.utils.dtos;

import com.bookevent.BookEventManager.entities.Invitation;
import com.bookevent.BookEventManager.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class EventDto {

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
    private String created_by;
    private String invitees;
    
    private List<InvitationDto> invites = new ArrayList<>();

}
