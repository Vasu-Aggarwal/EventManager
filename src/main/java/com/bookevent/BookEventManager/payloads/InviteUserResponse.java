package com.bookevent.BookEventManager.payloads;

import com.bookevent.BookEventManager.utils.dtos.EventDto;
import com.bookevent.BookEventManager.utils.dtos.InvitationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteUserResponse {
    private String message;
    private EventResponse event;
    private List<InvitationDto> invited_user;
}
