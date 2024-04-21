package com.bookevent.BookEventManager.payloads.requests;

import lombok.Data;

import java.util.List;

@Data
public class InviteUserRequest {
    private Integer invite_id;
    private Integer event;
    private List<String> invitedUser;
    private int is_invited;
}
