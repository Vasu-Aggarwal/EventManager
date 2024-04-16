package com.bookevent.BookEventManager.repositories;

import com.bookevent.BookEventManager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
