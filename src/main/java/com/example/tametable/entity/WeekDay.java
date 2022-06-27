package com.example.tametable.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "week_days")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "week_day")
    private Integer weekDay;

    @Column(name = "name")
    private String name;
}
