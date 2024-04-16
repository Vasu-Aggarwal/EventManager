package com.bookevent.BookEventManager.utils.dtos;

import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class EventDto {

    private Integer event_id;

    private Date start_time;
    private Duration duration;
    private int event_type;
    private String created_by;


}
