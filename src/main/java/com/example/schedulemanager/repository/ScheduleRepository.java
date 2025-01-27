package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleSaveResponseDto;
import com.example.schedulemanager.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleSaveResponseDto saveSchedule(Schedule schedule);
    List<ScheduleSaveResponseDto> findScheduleByAuthorOrModifiedDate(String author, String modifiedDate);
}
