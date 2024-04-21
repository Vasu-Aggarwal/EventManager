package com.bookevent.BookEventManager.payloads.responses;
import lombok.Data;

@Data
public class UserResponse {

    private String user_id;
    private String name;
    private String email;

}
