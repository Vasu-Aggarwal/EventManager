package com.bookevent.BookEventManager.utils.dtos;

import com.bookevent.BookEventManager.payloads.EventResponse;
import com.bookevent.BookEventManager.payloads.UserResponse;
import lombok.Data;

import java.util.List;

@Data
public class InvitationDto {

    private Integer invite_id;
    private UserResponse invitedUser;
    private int is_invited;
}
