package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleSaveResponseDto;
import com.example.schedulemanager.entity.Schedule;

public interface ScheduleRepository {
    ScheduleSaveResponseDto saveSchedule(Schedule schedule);
}
