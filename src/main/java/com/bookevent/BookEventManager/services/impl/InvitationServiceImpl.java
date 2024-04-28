package com.bookevent.BookEventManager.services.impl;

import com.bookevent.BookEventManager.entities.Event;
import com.bookevent.BookEventManager.entities.Invitation;
import com.bookevent.BookEventManager.entities.User;
import com.bookevent.BookEventManager.exceptions.ResourceNotFoundException;
import com.bookevent.BookEventManager.payloads.responses.EventResponse;
import com.bookevent.BookEventManager.payloads.requests.InviteUserRequest;
import com.bookevent.BookEventManager.payloads.responses.InviteUserResponse;
import com.bookevent.BookEventManager.repositories.EventRepository;
import com.bookevent.BookEventManager.repositories.InvitationRepository;
import com.bookevent.BookEventManager.repositories.UserRepository;
import com.bookevent.BookEventManager.services.InvitationService;
import com.bookevent.BookEventManager.utils.dtos.InvitationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InviteUserResponse inviteUser(InviteUserRequest inviteUserRequest) {
        //Search if user exists
        List<User> users = inviteUserRequest.getInvitedUser().stream()
                .map((userEmail) -> this.userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException(userEmail+" user not found"))).collect(Collectors.toList());

        //Search if event exists
        Event event = this.eventRepository.findById(inviteUserRequest.getEvent()).orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        List<Invitation> invitations = new ArrayList<>();

        for (User user: users){
            Invitation invitation = new Invitation();
            invitation.setInvited_user(user);
            invitation.setEvent(event);
            invitation.setIs_invited(1);

            invitations.add(invitation);
        }

        List<Invitation> invitationList = this.invitationRepository.saveAll(invitations);

        List<InvitationDto> invitationDtos = invitationList.stream().map((invite)-> this.modelMapper.map(invite, InvitationDto.class)).collect(Collectors.toList());

        return new InviteUserResponse("Invite has been sent to all the users", this.modelMapper.map(event, EventResponse.class), invitationDtos);
    }
}
