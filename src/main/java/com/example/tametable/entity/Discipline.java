package com.example.tametable.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "disciplines")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
