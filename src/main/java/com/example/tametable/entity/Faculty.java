package com.example.tametable.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "faculties")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
