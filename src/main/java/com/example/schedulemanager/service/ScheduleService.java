package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleSaveRequestDto;
import com.example.schedulemanager.dto.ScheduleSaveResponseDto;

public interface ScheduleService {
    ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto saveRequestDto);
}
