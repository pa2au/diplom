package com.example.tametable.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "times")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class TimeLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String time;

    @Column(name = "number_lesson")
    Integer numberLesson;
}
