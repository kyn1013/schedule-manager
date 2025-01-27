package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findScheduleByAuthorOrModifiedDate(Long memberId, String modifiedDate);
    Schedule findScheduleByIdOrElseThrow(Long id);
    List<ScheduleResponseDto> findScheduleByMemberIdOrElseThrow(Long memberId);
    Schedule findSchedulePasswordByIdOrElseThrow(Long id);
    int updateSchedule(Long id, String content, Long memberId);
    int deleteSchedule(Long id);

}
