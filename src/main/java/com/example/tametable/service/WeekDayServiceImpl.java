package com.example.tametable.service;

import com.example.tametable.repository.WeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekDayServiceImpl implements WeekDayService {
    private final WeekDayRepository weekDayRepository;

    @Override
    public List<com.example.tametable.entity.WeekDay> findWeekDayAll() {
        return weekDayRepository.findAll();
    }
}
