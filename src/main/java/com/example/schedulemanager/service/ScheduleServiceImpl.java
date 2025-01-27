package com.example.schedulemanager.service;

import com.example.schedulemanager.dto.ScheduleSaveRequestDto;
import com.example.schedulemanager.dto.ScheduleSaveResponseDto;
import com.example.schedulemanager.entity.Schedule;
import com.example.schedulemanager.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto saveRequestDto) {
        Schedule schedule = new Schedule(saveRequestDto.getContent(), saveRequestDto.getAuthor(), saveRequestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleSaveResponseDto> findAllSchedules(String author, String modifiedDate) {
        return scheduleRepository.findScheduleByAuthorOrModifiedDate(author, modifiedDate);
    }
}
