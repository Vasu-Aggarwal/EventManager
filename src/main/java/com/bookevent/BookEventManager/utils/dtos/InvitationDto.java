package com.bookevent.BookEventManager.utils.dtos;

import com.bookevent.BookEventManager.payloads.responses.UserResponse;
import lombok.Data;

@Data
public class InvitationDto {

    private Integer invite_id;
    private UserResponse invitedUser;
    private int is_invited;
}
