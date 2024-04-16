package com.bookevent.BookEventManager.utils.dtos;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private String comment_id;
    private String comment_content;
    private String user_commented_id;
    private Date comment_added;
    private Date comment_updated;

}
