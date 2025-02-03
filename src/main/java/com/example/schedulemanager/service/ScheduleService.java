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

    List<ScheduleResponseDto> findAllSchedules(String memberId, String modifiedDate);

    ScheduleResponseDto findByScheduleId(String id);

    List<ScheduleResponseDto> findByScheduleMemberId(String memberId);

    ScheduleResponseDto updateSchedule(String id, String password, String content, Long memberId);

    void deleteSchedule(String id, String password);

    List<ScheduleResponseDto> getScheduleList();

    Page<SchedulePagingResponseDto> list (int page, int size);

}
