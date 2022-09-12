
package com.hybird.entities;

import com.hybird.enums.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    private EnumRole id;

    private String name;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;
}