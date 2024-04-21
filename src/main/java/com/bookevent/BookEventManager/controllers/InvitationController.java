package com.bookevent.BookEventManager.controllers;

import com.bookevent.BookEventManager.payloads.InviteUserRequest;
import com.bookevent.BookEventManager.payloads.InviteUserResponse;
import com.bookevent.BookEventManager.services.InvitationService;
import com.bookevent.BookEventManager.utils.dtos.InvitationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitation")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping("/inviteUser")
    public ResponseEntity<InviteUserResponse> inviteUser(@RequestBody InviteUserRequest inviteUserRequest){
        InviteUserResponse newInvite = this.invitationService.inviteUser(inviteUserRequest);
        return new ResponseEntity<>(newInvite, HttpStatus.CREATED);
    }

}
