package com.example.schedulemanager.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long memberId;
    private String content;
    private String password;
}
