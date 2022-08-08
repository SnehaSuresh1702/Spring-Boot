package com.assess1.tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket{

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_category_id", insertable = false, updatable = false)
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "assignee_id", insertable = false, updatable = false)
    private AdminTeam adminTeam;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "priority_id", insertable = false, updatable = false)
    private Priority priority;


    @OneToMany(mappedBy = "ticket" , orphanRemoval = true , cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(generator = "sequence-generator-ticket")
    @GenericGenerator(
            name = "sequence-generator-ticket",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ticket_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1001"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    //@SequenceGenerator(name = "TicketId-generator",sequenceName = "TicketId-Sequence", initialValue = 1001,allocationSize = 100)
    private int ticket_id;

    private String category_id;
    private String sub_category_id;
    private String assignee_Id;
    private int reporter_Id;
    private String subject;
    private String description;
    private String status_id;
    private String priority_id;
    private LocalDateTime create_datetime = LocalDateTime.now();
    private LocalDateTime last_modified_datetime = LocalDateTime.now();
    //private LocalDateTime last_modified_datetime = create_datetime.minusDays(7);
}
