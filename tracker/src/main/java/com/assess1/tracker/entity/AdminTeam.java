package com.assess1.tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_team")
public class AdminTeam {

    @Id
    @Column(name = "admin_id")
    private String admin_id;
    private String assignee;
    private String email_Id;
}
