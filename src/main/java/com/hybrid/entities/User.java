package com.hybrid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{NotBlank.user.username}")
    @Column(name = "user_name")
    private String username;

    @NotBlank(message = "{NotBlank.user.password}")
    @Size(min = 6, message = "{Size.user.password}")
    private String password;

    @NotBlank(message = "{NotBlank.user.fullname}")
    @Column(name = "full_name")
    private String fullname;

    private Integer status = 1;

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> role = new HashSet<>();


}
