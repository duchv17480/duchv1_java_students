package com.hybrid.entities;

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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "{NotBlank.subjects.name}")
    private String name;

    @Min(value = 0,message = "{Min.subjects.credits}")
    @Max(value = 10,message = "{Max.subjects.credits}")
    @NotNull(message = "{NotNull.subjects.credits}")
    private Integer credits;

    @Min(value = 0,message = "{Min.subjects.semester}")
    @Max(value = 10,message = "{Max.subjects.semester}")
    @NotNull(message = "{NotNull.subjects.semester}")
    private Integer semester;

    @OneToMany(mappedBy = "subjectId")
    List<Marks> marksList;

}
