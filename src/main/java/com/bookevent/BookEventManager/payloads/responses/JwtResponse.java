package com.bookevent.BookEventManager.payloads.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private String token;

}
