package com.assess1.tracker.DTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ViewDTO {

    private int ticket_id;
    private String category_desc;
    private String sub_category_desc;
    private String assignee;
    private String subject;
    private String description;
    private String status;
    private String priority;
    private String url;
}
