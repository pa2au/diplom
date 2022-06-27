package com.example.tametable.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListLessonTeacher {
    private Long idLesson;

    private String discipline;

    private String weekDay;

    private String timeLesson;

    private Boolean isLektion;

    private Boolean weekTypeChislitel;

    private Boolean weekTypeZnamenatel;

    private String group;

    private String link;

    private String link2;


}
