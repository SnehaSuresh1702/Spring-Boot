package com.assess1.tracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "sequence-generator-user")
    //(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "sequence-generator-user",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    //@SequenceGenerator(name = "UserId-generator",sequenceName = "UserId-Sequence", initialValue = 1,allocationSize = 100)
    private Integer user_id;
    @NotEmpty
    @Column(name = "name",nullable = false)
    private String name;
    @NotEmpty
    @Column(unique = true)
    @Email
    private String email_Id;
    @JsonFormat(pattern = "dd-MMM-yyyy h:mm a" , shape = JsonFormat.Shape.STRING)
    private LocalDateTime create_datetime = LocalDateTime.now();
    @JsonFormat(pattern = "dd-MMM-yyyy h:mm a" , shape = JsonFormat.Shape.STRING)
    private LocalDateTime last_modified_datetime = LocalDateTime.now();
}

