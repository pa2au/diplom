package com.example.tametable.repository;

import com.example.tametable.entity.TimeLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLessonRepo extends JpaRepository<TimeLesson, Long> {
}
