package com.hybird.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table()
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Students studentId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subjects subjectsId;

    private Integer mark;
}
