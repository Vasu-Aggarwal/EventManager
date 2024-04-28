package com.bookevent.BookEventManager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    private Integer role_id;
    private String role;
}
