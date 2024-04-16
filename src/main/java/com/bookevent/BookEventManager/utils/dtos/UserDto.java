package com.bookevent.BookEventManager.utils.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private String user_id;

    @NotNull
    @NotEmpty
    @Size(min = 4, message = "Minimum password length is 4")
    private String password;

    @Size(min = 2, message = "Minimum name length is 2")
    private String name;

    @Email(message = "Enter a valid email")
    private String email;
    private String username;

}
