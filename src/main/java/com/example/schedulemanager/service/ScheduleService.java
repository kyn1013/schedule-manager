package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto saveRequestDto);

    List<ScheduleResponseDto> findAllSchedules(Long memberId, String modifiedDate);

    ScheduleResponseDto findByScheduleId(Long id);

    List<ScheduleResponseDto> findByScheduleMemberId(Long memberId);

    ScheduleResponseDto updateSchedule(Long id, String password, String content, Long memberId);

    void deleteSchedule(Long id);

}
