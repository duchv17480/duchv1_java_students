package com.hybird.dtos;

import com.hybird.enums.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String birthDay;

    private EnumGender gender;

    private String birthPlace;

    private String address;

    private String phone;

    private String photo;

    private Integer entryPont;

    private String enrollmentObj;

    private String section;
}
