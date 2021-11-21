package com.tinotenda.usertask.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    @NotBlank(message = "Username is mandatory")
    private String userName;

    @Column(name = "first_name")
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;
}
