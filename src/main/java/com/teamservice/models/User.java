package com.teamservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
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
    @JoinColumn(name = "group_id")
    private Group group;
}
