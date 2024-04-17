package com.bookevent.BookEventManager.utils.dtos;

import com.bookevent.BookEventManager.entities.Event;
import com.bookevent.BookEventManager.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class InvitationDto {

    private Integer invite_id;
    private Event event;
    private User invitedUser;

}
