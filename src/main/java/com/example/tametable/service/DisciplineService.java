package com.example.tametable.service;

import com.example.tametable.entity.Discipline;

import java.util.List;

public interface DisciplineService {
    List<Discipline> findDisciplineAll();

    void saveDiscipline(String name, String description);

    void updateDiscipline(String name, String description, int id);

    void delete(int id);

    Discipline findDisciplineById(int id);
}
