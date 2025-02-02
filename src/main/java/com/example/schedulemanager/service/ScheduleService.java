package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.SchedulePagingResponseDto;
import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto saveRequestDto);

    List<ScheduleResponseDto> findAllSchedules(Long memberId, String modifiedDate);

    ScheduleResponseDto findByScheduleId(Long id);

    List<ScheduleResponseDto> findByScheduleMemberId(Long memberId);

    ScheduleResponseDto updateSchedule(Long id, String password, String content, Long memberId);

    void deleteSchedule(Long id);

    List<ScheduleResponseDto> getScheduleList();

    Page<SchedulePagingResponseDto> list (int page, int size);

}
