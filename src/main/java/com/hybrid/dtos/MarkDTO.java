package com.hybrid.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MarkDTO {
    private Integer id;

    private Integer studentId;

    private Integer subjectId;

    private Integer mark;

}
