package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto saveRequestDto);

    List<ScheduleResponseDto> findAllSchedules(String author, String modifiedDate);

    ScheduleResponseDto findByScheduleId(Long id);

    ScheduleResponseDto updateSchedule(Long id, String password, String content, String author);

    void deleteSchedule(Long id);

}
