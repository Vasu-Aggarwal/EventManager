package com.bookevent.BookEventManager.services.impl;

import com.bookevent.BookEventManager.entities.Event;
import com.bookevent.BookEventManager.entities.Invitation;
import com.bookevent.BookEventManager.entities.User;
import com.bookevent.BookEventManager.exceptions.BadRequest;
import com.bookevent.BookEventManager.exceptions.ResourceNotFoundException;
import com.bookevent.BookEventManager.payloads.requests.CreateEventRequest;
import com.bookevent.BookEventManager.payloads.responses.EventResponse;
import com.bookevent.BookEventManager.payloads.responses.InviteUserResponse;
import com.bookevent.BookEventManager.payloads.responses.UserResponse;
import com.bookevent.BookEventManager.repositories.EventRepository;
import com.bookevent.BookEventManager.repositories.InvitationRepository;
import com.bookevent.BookEventManager.repositories.UserRepository;
import com.bookevent.BookEventManager.services.EventService;
import com.bookevent.BookEventManager.utils.dtos.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public EventResponse createEvent(CreateEventRequest createEventRequest) {

        User user = this.userRepository.findById(createEventRequest.getCreated_by())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event event = this.modelMapper.map(createEventRequest, Event.class);
        event.setStart_date(createEventRequest.getStart_date());
        event.setStart_time(createEventRequest.getStart_time());
        event.setEnd_date(createEventRequest.getEnd_date());
        event.setEvent_type(createEventRequest.getEvent_type());
        event.setCreated_by(user);
        ResponseEntity<InviteUserResponse> inviteUserResponse = null;
        Event savedEvent;

        if (createEventRequest.getInvitees().isEmpty()){
            savedEvent = this.eventRepository.save(event);
        } else {
            //send invite to the users
            createEventRequest.getInvitees().stream()
                    .map((userEmail) -> {
                        ResponseEntity<UserResponse> addUserResponse = null;
                        if (!this.userRepository.findByEmail(userEmail).isPresent()){
                            Map<String, Object> addUserJsonBody = new HashMap<>();
                            addUserJsonBody.put("name", userEmail);
                            addUserJsonBody.put("email", userEmail);
                            addUserJsonBody.put("password", "");
                            addUserJsonBody.put("is_account", 0);
                            HttpEntity<Map<String, Object>> request = new HttpEntity<>(addUserJsonBody);
                            try {
                                 addUserResponse = this.restTemplate.postForEntity("http://localhost:9090/api/user/addUser", request, UserResponse.class);
                            } catch (HttpClientErrorException e){
                                throw new HttpClientErrorException(e.getStatusCode(), e.getResponseBodyAsString());
                            }
                        }
                        return addUserResponse;
                    });

            savedEvent = this.eventRepository.save(event);

            Map<String, Object> inviteUserJsonBody = new HashMap<>();
            inviteUserJsonBody.put("is_invited", 1);
            inviteUserJsonBody.put("event", savedEvent.getEvent_id());
            inviteUserJsonBody.put("invitedUser", createEventRequest.getInvitees());
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(inviteUserJsonBody);

            try {
                inviteUserResponse = this.restTemplate.postForEntity("http://localhost:9090/api/invitation/inviteUser", request, InviteUserResponse.class);
            } catch (HttpClientErrorException e){
                throw new HttpClientErrorException(e.getStatusCode(), e.getResponseBodyAsString());
            }

        }
        List<Invitation> invitations = inviteUserResponse.getBody().getInvited_user().stream().map((invite)-> this.modelMapper.map(invite, Invitation.class)).collect(Collectors.toList());
        savedEvent.setInvitations(invitations);

        return this.modelMapper.map(savedEvent, EventResponse.class);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        Event event = this.eventRepository.findById(eventDto.getEvent_id())
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exists"));
        event.setEvent_type(eventDto.getEvent_type());
        event.setEnd_date(eventDto.getEnd_date());
        return this.modelMapper.map(event, EventDto.class);
    }

    @Override
    public void deleteAnyEvent(Integer event_id) {
        //ADMIN FUNCTIONALITY
        Event event = this.eventRepository.findById(event_id)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exists"));
        this.eventRepository.delete(event);
    }

    @Override
    public EventDto getEventById(Integer event_id) {
        Event event = this.eventRepository.findById(event_id)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exists"));
        return this.modelMapper.map(event, EventDto.class);
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = this.eventRepository.findAll();
        return events.stream()
                .map((event) -> this.modelMapper.map(event, EventDto.class))
                .collect(Collectors.toList());
    }

    public boolean isValidTimeFormat(String userDate, String format){
        DateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try{
            sdf.parse(userDate);
        } catch (ParseException e){
            return false;
        }
        return true;
    }
}
