package com.assess1.tracker.DTO;

import com.assess1.tracker.entity.Comment;
import com.assess1.tracker.entity.Ticket;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class TicketCommentDTO {

    private int ticket_id;
    private String category_desc;
    private String sub_category_desc;
    private String assignee;
    private String subject;
    private String description;
    private String status;
    private String priority;
    private String url;

    private List<CommentDTO> comments;

}
