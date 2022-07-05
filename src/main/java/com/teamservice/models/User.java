package com.teamservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    private Long telegramId;

    private String firstName;

    private String lastName;

    private Date lastModified;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    @EqualsAndHashCode.Exclude
    private Group group;

    public User(Long telegramId, String firstName, String lastName) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
