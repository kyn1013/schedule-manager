package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findScheduleByAuthorOrModifiedDate(String author, String modifiedDate);
    Schedule findScheduleByIdOrElseThrow(Long id);
    Schedule findSchedulePasswordByIdOrElseThrow(Long id);
    int updateSchedule(Long id, String content, String author);
    int deleteSchedule(Long id);

}
