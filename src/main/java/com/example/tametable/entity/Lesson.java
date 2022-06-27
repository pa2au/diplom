package com.example.tametable.entity;

import com.example.tametable.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    Discipline discipline;

    @ManyToOne
    Group group;

    @ManyToOne
    @JoinColumn(name = "week_day_id", referencedColumnName = "id")
    WeekDay weekDay;

    @ManyToOne
    @JoinColumn(name = "teacher_user_id", referencedColumnName = "id")
    User user;

    @ManyToOne
    @JoinColumn(name = "time_id", referencedColumnName = "id")
    TimeLesson timeLesson;

    boolean isLection;

    boolean weekTypeChislitel;

    boolean weekTypeZnamenatel;

    String link;

    String link2;

    @Enumerated(EnumType.STRING)
    Status status;
}