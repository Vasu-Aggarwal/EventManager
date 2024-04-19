package com.bookevent.BookEventManager.services.impl;

import com.bookevent.BookEventManager.entities.Event;
import com.bookevent.BookEventManager.entities.Invitation;
import com.bookevent.BookEventManager.entities.User;
import com.bookevent.BookEventManager.exceptions.BadRequest;
import com.bookevent.BookEventManager.exceptions.ResourceNotFoundException;
import com.bookevent.BookEventManager.payloads.CreateEventRequest;
import com.bookevent.BookEventManager.repositories.EventRepository;
import com.bookevent.BookEventManager.repositories.InvitationRepository;
import com.bookevent.BookEventManager.repositories.UserRepository;
import com.bookevent.BookEventManager.services.EventService;
import com.bookevent.BookEventManager.utils.dtos.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Override
    public EventDto createEvent(CreateEventRequest createEventRequest) {

        User user = this.userRepository.findById(createEventRequest.getCreated_by())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Event event = this.modelMapper.map(createEventRequest, Event.class);
        //check if format provided by the user is correct or no
        event.setStart_date(createEventRequest.getStart_date());
        event.setStart_time(createEventRequest.getStart_time());
        event.setEnd_date(createEventRequest.getEnd_date());
//        event.setInvitations(createEventRequest.getInvitees());
        event.setCreated_by(user);
        Event savedEvent = this.eventRepository.save(event);

        return this.modelMapper.map(savedEvent, EventDto.class);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        Event event = this.eventRepository.findById(eventDto.getEvent_id())
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exists"));
        event.setEvent_type(eventDto.getEvent_type());
        event.setEnd_date(eventDto.getEnd_date());
//        event.setStart_time(eventDto.getStart_time());
        event.setInvitations(eventDto.getInvites()
                .stream().map((invite) -> this.modelMapper.map(invite, Invitation.class)).collect(Collectors.toList()));
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
