package com.bookevent.BookEventManager.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;
    private String comment_content;
    private String user_commented_id;
    private Date comment_added;
    private Date comment_updated;

}
