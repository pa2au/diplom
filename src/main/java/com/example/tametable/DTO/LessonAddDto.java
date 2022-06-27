package com.example.tametable.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonAddDto {

    private Integer teacherId;

    private Integer disciplineId;

    private Integer weekId;

    private Long timeLessonId;

    private boolean lecture;

    private boolean weekTypeChislitel;

    private boolean weekTypeZnamenatel;

    private Integer groupId;

    private String link;

    private String link2;
}
