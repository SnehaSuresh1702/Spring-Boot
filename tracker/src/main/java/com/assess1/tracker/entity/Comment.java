package com.assess1.tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(generator = "sequence-generator-comment")
    @GenericGenerator(
            name = "sequence-generator-comment",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "comment_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "101"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private int comment_id;
    private int ticket_id;
    private int user_id;
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    private Ticket ticket;

    @ManyToOne
    private User user;
}
