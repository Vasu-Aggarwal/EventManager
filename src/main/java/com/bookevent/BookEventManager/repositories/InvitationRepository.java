package com.bookevent.BookEventManager.repositories;

import com.bookevent.BookEventManager.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
}
