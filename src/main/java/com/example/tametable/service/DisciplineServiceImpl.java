package com.example.tametable.service;

import com.example.tametable.entity.Discipline;
import com.example.tametable.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepository disciplineRepository;

    @Override
    public List<Discipline> findDisciplineAll() {
        return disciplineRepository.findAll();
    }

    @Override
    public void saveDiscipline(String name, String description) {
        Discipline discipline = Discipline.builder()
                .name(name)
                .description(description)
                .build();
        disciplineRepository.save(discipline);
    }

    @Override
    public void updateDiscipline(String name, String description, int id) {
        Discipline discipline = findDisciplineById(id);
        discipline.setName(name);
        discipline.setDescription(description);
        disciplineRepository.save(discipline);
    }

    @Override
    public void delete(int id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public Discipline findDisciplineById(int id) {
        return disciplineRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException(String.format("Entity with %d doesn't exist", id)));
    }
}
