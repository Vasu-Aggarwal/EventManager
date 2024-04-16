package com.bookevent.BookEventManager.repositories;

import com.bookevent.BookEventManager.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
