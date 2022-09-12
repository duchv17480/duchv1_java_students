package com.hybird.entities;

import com.hybird.enums.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_day")
    private String birthDay;

    private EnumGender gender;

    @Column(name = "birth_place")
    private String birthPlace;

    private String address;

    private String phone;

    private String photo;

    @Column(name = "entry_point")
    private Integer entryPont;

    @Column(name = "enrollment_obj")
    private String enrollmentObj;

    private String section;

    @OneToMany(mappedBy = "studentId")
    List<Marks> marksList;


}
