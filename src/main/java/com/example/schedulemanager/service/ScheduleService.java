package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleSaveRequestDto;
import com.example.schedulemanager.dto.ScheduleSaveResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto saveRequestDto);

    List<ScheduleSaveResponseDto> findAllSchedules(String author, String modifiedDate);

}
