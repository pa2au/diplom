package com.example.tametable.service;

import com.example.tametable.entity.TimeLesson;
import com.example.tametable.enums.Status;
import com.example.tametable.repository.LessonRepo;
import com.example.tametable.repository.TimeLessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeLessonsServiceImpl implements TimeLessonsService {
    private final TimeLessonRepo timeLessonRepo;

    @Autowired
    private LessonRepo lessonRepo;

    @Override
    public List<TimeLesson> findExerciseTimeAll() {
        return timeLessonRepo.findAll();
    }


}
