package com.bookevent.BookEventManager.utils.dtos;

import com.bookevent.BookEventManager.entities.Role;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.lang.annotation.Repeatable;
import java.util.HashSet;
import java.util.Set;

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
    @UniqueElements
    private String email;
    private String username;

    private int is_account;

    private Set<Role> roles = new HashSet<>();

}
