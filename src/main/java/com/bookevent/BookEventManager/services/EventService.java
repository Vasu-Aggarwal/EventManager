package com.bookevent.BookEventManager.services;

import com.bookevent.BookEventManager.utils.dtos.EventDto;

import java.text.ParseException;
import java.util.List;

public interface EventService {

    //CREATE EVENT
    EventDto createEvent(EventDto eventDto) throws ParseException;

    //UPDATE EVENT BY USER
    EventDto updateEvent(EventDto eventDto);

    //DELETE ANY EVENT ---> ADMIN
    void deleteAnyEvent(Integer event_id);

    //GET EVENT BY ID ---> CHECK IF EVENT BELONGS TO USER OR NOT, IF ADMIN THEN ALLOW
    EventDto getEventById(Integer event_id);

    //GET ALL EVENTS -----> ADMIN
    List<EventDto> getAllEvents();

}
