package com.example.tametable.service;

import com.example.tametable.entity.Faculty;
import com.example.tametable.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }
}
