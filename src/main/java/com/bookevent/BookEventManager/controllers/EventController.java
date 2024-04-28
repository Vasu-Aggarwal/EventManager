package com.bookevent.BookEventManager.controllers;

import com.bookevent.BookEventManager.payloads.responses.ApiResponse;
import com.bookevent.BookEventManager.payloads.requests.CreateEventRequest;
import com.bookevent.BookEventManager.payloads.responses.EventResponse;
import com.bookevent.BookEventManager.services.EventService;
import com.bookevent.BookEventManager.utils.dtos.EventDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    //CREATE EVENT
    @PostMapping("/createEvent")
    public ResponseEntity<EventResponse> createEvent(@RequestBody CreateEventRequest createEventRequest) throws ParseException {
        EventResponse newEvent = this.eventService.createEvent(createEventRequest);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    //UPDATE EVENT BY USER
    @PutMapping("/updateEvent")
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto eventDto){
        EventDto updateEvent = this.eventService.updateEvent(eventDto);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    //UPDATE ANY EVENT ---> ADMIN
    @PutMapping("/updateEventAdmin")
    public ResponseEntity<EventDto> updateAnyEvent(@RequestBody EventDto eventDto){
        EventDto updateEvent = this.eventService.updateEvent(eventDto);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    //DELETE ANY EVENT ---> ADMIN
    @DeleteMapping("/deleteEventAdmin/{event_id}")
    public ResponseEntity<ApiResponse> deleteAnyEvent(@PathVariable Integer event_id){
        this.eventService.deleteAnyEvent(event_id);
        return new ResponseEntity<>(new ApiResponse("Event deleted successfully", true, 1), HttpStatus.OK);
    }

    //GET EVENT BY ID ---> CHECK IF EVENT BELONGS TO USER OR NOT, IF ADMIN THEN ALLOW
    @GetMapping("/{event_id}")
    @ResponseBody
    public ResponseEntity<EventDto> getEventById(@PathVariable Integer event_id, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return new ResponseEntity<>(this.eventService.getEventById(event_id, token), HttpStatus.OK);
    }

    //GET ALL EVENTS -----> ADMIN
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<EventDto>> getEventById(){
        List<EventDto> eventDtoList = this.eventService.getAllEvents();
        return new ResponseEntity<>(eventDtoList, HttpStatus.OK);
    }

}
