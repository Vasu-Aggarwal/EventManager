package com.bookevent.BookEventManager.repositories;

import com.bookevent.BookEventManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
