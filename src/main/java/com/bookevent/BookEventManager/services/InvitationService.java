package com.bookevent.BookEventManager.services;

import com.bookevent.BookEventManager.payloads.requests.InviteUserRequest;
import com.bookevent.BookEventManager.payloads.responses.InviteUserResponse;

public interface InvitationService {

    //SEND INVITATION
    InviteUserResponse inviteUser(InviteUserRequest inviteUserRequest);

    //UPDATE INVITATION

    //GET ALL INVITATION LIST

}
