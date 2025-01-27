package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto saveRequestDto) {
        Schedule schedule = new Schedule(saveRequestDto.getContent(), saveRequestDto.getMemberId(), saveRequestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(Long memberId, String modifiedDate) {
        return scheduleRepository.findScheduleByAuthorOrModifiedDate(memberId, modifiedDate);
    }

    @Override
    public ScheduleResponseDto findByScheduleId(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findByScheduleMemberId(Long memberId) {
        return scheduleRepository.findScheduleByMemberIdOrElseThrow(memberId);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String content, Long memberId) {
        // 수정할 일정의 비밀번호 조회
        Schedule schedule = scheduleRepository.findSchedulePasswordByIdOrElseThrow(id);
        if (!schedule.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, content, memberId);
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정된 데이터가 없습니다");
        }
        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        int deletedRow = scheduleRepository.deleteSchedule(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

}
