package com.bookevent.BookEventManager.repositories;

import com.bookevent.BookEventManager.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
