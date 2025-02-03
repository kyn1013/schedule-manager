package com.example.schedulemanager.repository;

import com.example.schedulemanager.common.exception.ScheduleNotFoundException;
import com.example.schedulemanager.dto.SchedulePagingResponseDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Member;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findScheduleByAuthorOrModifiedDate(Long memberId, String modifiedDate);
    Schedule findScheduleByIdOrElseThrow(Long id) throws ScheduleNotFoundException;
    List<ScheduleResponseDto> findScheduleByMemberIdOrElseThrow(Long memberId);
    Schedule findSchedulePasswordByIdOrElseThrow(Long id);
    int updateSchedule(Long id, String content, Long memberId);
    int deleteSchedule(Long id);

    List<SchedulePagingResponseDto> findSchedules(Long offset, int size);

    int findScheduleSize();

    Member findMemberById(Long memberId);

}
