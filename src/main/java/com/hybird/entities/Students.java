package com.hybird.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Can not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Can not be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Can not be empty")
    @Column(name = "birth_day")
    private String birthDay;

    private String gender;

    @NotBlank(message = "Can not be empty")
    @Column(name = "birth_place")
    private String birthPlace;

    @NotBlank(message = "Can not be empty")
    private String address;

    @NotBlank(message = "Can not be empty")
    private String phone;

    @Min(value = 1, message = "than 1")
    @Max(value = 10, message = "below 10")
    @NotNull(message = "Can not be empty")
    @Column(name = "entry_point")
    private Integer entryPont;

    @Column(name = "enrollment_obj")
    private String enrollmentObj;

    private String section;

    @OneToMany(mappedBy = "studentId")
    List<Marks> marksList;


}
