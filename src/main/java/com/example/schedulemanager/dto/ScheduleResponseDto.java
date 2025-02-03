package com.example.schedulemanager.dto;

import com.example.schedulemanager.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String content;
    private Long memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.memberId = schedule.getMemberId();
        this.createdAt = schedule.getCreateDate();
        this.updatedAt = schedule.getModifiedDate();
    }


}
