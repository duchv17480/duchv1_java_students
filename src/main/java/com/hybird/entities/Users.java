package com.hybird.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @NotBlank(message = "* user name not empty")
    @Column(name = "user_name")
    private String username;

    @NotBlank(message = "* password not empty")
    @Size(min = 6, message = "pass min 6 characters")
    private String password;

    @NotBlank(message = "* fullname not empty")
    @Column(name = "full_name")
    private String fullname;

    private Integer status = 1;

    @OneToOne(mappedBy = "users")
    private Roles rolesList;
}
