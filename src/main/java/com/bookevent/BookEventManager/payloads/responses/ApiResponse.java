package com.bookevent.BookEventManager.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Boolean success;
    private int status;    //Error=0, working fine=1
}
