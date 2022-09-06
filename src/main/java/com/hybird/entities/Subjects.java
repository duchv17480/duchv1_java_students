package com.hybird.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name not null")
    private String name;

    @Min(value = 0,message = "min 0")
    @Max(value = 10,message = "max 10")
    @NotNull(message = "not null")
    private Integer credits;

    @Min(value = 0,message = "min 0")
    @Max(value = 10,message = "max 10")
    @NotNull(message = "not null")
    private Integer semester;

    @OneToMany(mappedBy = "subjectsId")
    List<Marks> marksList;

}
