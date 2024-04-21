package com.bookevent.BookEventManager.services;

import com.bookevent.BookEventManager.payloads.InviteUserRequest;
import com.bookevent.BookEventManager.payloads.InviteUserResponse;
import com.bookevent.BookEventManager.utils.dtos.InvitationDto;

import java.util.List;

public interface InvitationService {

    //SEND INVITATION
    InviteUserResponse inviteUser(InviteUserRequest inviteUserRequest);

    //UPDATE INVITATION

    //GET ALL INVITATION LIST

}
