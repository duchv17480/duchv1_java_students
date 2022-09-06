
package com.hybird.entities;

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
@Table()
public class Roles {
    @Id
    private String id;

    private String name;

    @OneToOne
    @JoinColumn(name = "username")
    private Users users;
}