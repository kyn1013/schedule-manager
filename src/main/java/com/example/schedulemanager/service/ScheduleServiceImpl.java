package com.example.schedulemanager.service;

import com.example.schedulemanager.common.code.ErrorCode;
import com.example.schedulemanager.common.exception.InvalidPasswordException;
import com.example.schedulemanager.common.util.ValidationUtils;
import com.example.schedulemanager.dto.SchedulePagingResponseDto;
import com.example.schedulemanager.dto.ScheduleRequestDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<ScheduleResponseDto> findAllSchedules(String memberId, String modifiedDate) {
        Long numberId = ValidationUtils.validNumberInputValue(memberId);
        return scheduleRepository.findScheduleByAuthorOrModifiedDate(numberId, modifiedDate);
    }

    @Override
    public ScheduleResponseDto findByScheduleId(String id) {
        Long numberId = ValidationUtils.validNumberInputValue(id);
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(numberId);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findByScheduleMemberId(String memberId) {
        Long numberId = ValidationUtils.validNumberInputValue(memberId);
        return scheduleRepository.findScheduleByMemberIdOrElseThrow(numberId);
    }

    @Override
    public ScheduleResponseDto updateSchedule(String id, String password, String content, Long memberId) {
        Long scheduleNumberId = ValidationUtils.validNumberInputValue(id);
        // 수정할 일정의 비밀번호 조회
        Schedule schedule = scheduleRepository.findSchedulePasswordByIdOrElseThrow(scheduleNumberId);
        if (!schedule.getPassword().equals(password)){
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD_EXCEPTION);
        }

        int updatedRow = scheduleRepository.updateSchedule(scheduleNumberId, content, memberId);
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정된 데이터가 없습니다");
        }
        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleNumberId);
        return new ScheduleResponseDto(updatedSchedule);
    }

    @Override
    public void deleteSchedule(String id, String password) {
        Long numberId = ValidationUtils.validNumberInputValue(id);
        Schedule schedule = scheduleRepository.findSchedulePasswordByIdOrElseThrow(numberId);
        if (!schedule.getPassword().equals(password)){
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD_EXCEPTION);
        }
        scheduleRepository.deleteSchedule(numberId);
    }

    @Override
    public List<ScheduleResponseDto> getScheduleList() {
        return null;
    }

    @Override
    public Page<SchedulePagingResponseDto> list(int page, int size) {
        int count = scheduleRepository.findScheduleSize();
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(scheduleRepository.findSchedules(pageable.getOffset(), pageable.getPageSize()), pageable, count);
    }

}
