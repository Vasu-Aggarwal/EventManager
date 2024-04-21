package com.bookevent.BookEventManager.utils.dtos;

import com.bookevent.BookEventManager.entities.Invitation;
import com.bookevent.BookEventManager.payloads.InviteUserResponse;
import com.bookevent.BookEventManager.payloads.UserResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.sql.Time;
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
    private UserResponse created_by;
    private InviteUserResponse invitees;

    private List<InvitationDto> invites = new ArrayList<>();
}
